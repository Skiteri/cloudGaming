#define NOMINMAX
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <d3d11.h>
#include <dxgi1_2.h>
#include <jni.h>
#include <iostream>
#include <mutex>
#include <vector>

#pragma comment(lib, "d3d11.lib")
#pragma comment(lib, "dxgi.lib")

class ScreenCapture {
public:
    ID3D11Device*           d3dDevice       = nullptr;
    ID3D11DeviceContext*    d3dContext      = nullptr;
    IDXGIOutputDuplication* deskDupl        = nullptr;
    ID3D11Texture2D*        stagingTexture  = nullptr;

    int width  = 0;
    int height = 0;
    bool initialized = false;

    std::mutex mtx;

    // Полное уничтожение всех ресурсов
    void releaseAll() {
        if (stagingTexture) { stagingTexture->Release(); stagingTexture = nullptr; }
        if (deskDupl)       { deskDupl->Release();       deskDupl = nullptr; }
        if (d3dContext)     { d3dContext->Release();     d3dContext = nullptr; }
        if (d3dDevice)      { d3dDevice->Release();      d3dDevice = nullptr; }
        initialized = false;
        width = height = 0;
    }

    ~ScreenCapture() {
        releaseAll();
    }

    // Пересоздание только дубликатора + staging (при ACCESS_LOST)
    bool recreateDuplication() {
        if (deskDupl) {
            deskDupl->Release();
            deskDupl = nullptr;
        }
        if (stagingTexture) {
            stagingTexture->Release();
            stagingTexture = nullptr;
        }

        IDXGIDevice* dxgiDevice = nullptr;
        HRESULT hr = d3dDevice->QueryInterface(__uuidof(IDXGIDevice), (void**)&dxgiDevice);
        if (FAILED(hr)) return false;

        IDXGIAdapter* dxgiAdapter = nullptr;
        hr = dxgiDevice->GetParent(__uuidof(IDXGIAdapter), (void**)&dxgiAdapter);
        dxgiDevice->Release();
        if (FAILED(hr)) return false;

        IDXGIOutput* dxgiOutput = nullptr;
        hr = dxgiAdapter->EnumOutputs(0, &dxgiOutput);
        dxgiAdapter->Release();
        if (FAILED(hr)) return false;

        IDXGIOutput1* dxgiOutput1 = nullptr;
        hr = dxgiOutput->QueryInterface(__uuidof(IDXGIOutput1), (void**)&dxgiOutput1);
        if (FAILED(hr)) {
            dxgiOutput->Release();
            return false;
        }

        hr = dxgiOutput1->DuplicateOutput(d3dDevice, &deskDupl);
        if (FAILED(hr)) {
            dxgiOutput1->Release();
            dxgiOutput->Release();
            return false;
        }

        DXGI_OUTPUT_DESC outputDesc{};
        dxgiOutput->GetDesc(&outputDesc);

        DXGI_OUTDUPL_DESC duplDesc;
        deskDupl->GetDesc(&duplDesc);
        width = duplDesc.ModeDesc.Width;
        height = duplDesc.ModeDesc.Height;

        dxgiOutput1->Release();
        dxgiOutput->Release();

        // Staging texture
        D3D11_TEXTURE2D_DESC desc{};
        desc.Width              = width;
        desc.Height             = height;
        desc.MipLevels          = 1;
        desc.ArraySize          = 1;
        desc.Format             = DXGI_FORMAT_B8G8R8A8_UNORM;
        desc.SampleDesc.Count   = 1;
        desc.Usage              = D3D11_USAGE_STAGING;
        desc.CPUAccessFlags     = D3D11_CPU_ACCESS_READ;

        hr = d3dDevice->CreateTexture2D(&desc, nullptr, &stagingTexture);
        if (FAILED(hr)) {
            deskDupl->Release();
            deskDupl = nullptr;
            return false;
        }

        return true;
    }

    bool init() {
        
        std::lock_guard<std::mutex> lock(mtx);
        if (initialized) return true;

        HMODULE user32 = LoadLibraryW(L"user32.dll");
            if (user32) {
                using SetProcessDpiAwarenessContext_t = HRESULT(WINAPI*)(DPI_AWARENESS_CONTEXT);
                auto pSetProcessDpiAwarenessContext =
                    reinterpret_cast<SetProcessDpiAwarenessContext_t>(
                        GetProcAddress(user32, "SetProcessDpiAwarenessContext"));

                if (pSetProcessDpiAwarenessContext) {
                    pSetProcessDpiAwarenessContext(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2);
                } else {
                    // Fallback для старых систем
                    using SetProcessDPIAware_t = BOOL(WINAPI*)();
                    auto pSetProcessDPIAware =
                        reinterpret_cast<SetProcessDPIAware_t>(
                            GetProcAddress(user32, "SetProcessDPIAware"));
                    if (pSetProcessDPIAware) {
                        pSetProcessDPIAware();
                    }
                }
                FreeLibrary(user32);
            }

        releaseAll(); // на всякий случай

        D3D_FEATURE_LEVEL featureLevel;
        HRESULT hr = D3D11CreateDevice(
            nullptr,
            D3D_DRIVER_TYPE_HARDWARE,
            nullptr,
            0,
            nullptr, 0,
            D3D11_SDK_VERSION,
            &d3dDevice,
            &featureLevel,
            &d3dContext
        );

        if (FAILED(hr)) {
            return false;
        }

        if (!recreateDuplication()) {
            releaseAll();
            return false;
        }

        initialized = true;

        return true;
    }
};

static ScreenCapture g_capture;

// ====================== Захват кадра ======================
jbyteArray captureFrame(JNIEnv* env) {
    if (!g_capture.initialized) {
        if (!g_capture.init()) return nullptr;
    }

    std::lock_guard<std::mutex> lock(g_capture.mtx);

    DXGI_OUTDUPL_FRAME_INFO frameInfo{};
    IDXGIResource* desktopResource = nullptr;
    ID3D11Texture2D* gpuTexture = nullptr;
    jbyteArray result = nullptr;

    HRESULT hr = g_capture.deskDupl->AcquireNextFrame(7, &frameInfo, &desktopResource); // 16 мс таймаут

    // Нет нового кадра — это нормально
    if (hr == DXGI_ERROR_WAIT_TIMEOUT) {
        return nullptr;
    }

    // Дубликатор умер (разрешение изменилось / fullscreen / и т.д.)
    if (hr == DXGI_ERROR_ACCESS_LOST) {
        if (!g_capture.recreateDuplication()) {
            g_capture.initialized = false;
            return nullptr;
        }
        // Пробуем ещё раз после пересоздания
        hr = g_capture.deskDupl->AcquireNextFrame(16, &frameInfo, &desktopResource);
        if (FAILED(hr) || !desktopResource) {
            return nullptr;
        }
    }

    if (FAILED(hr) || !desktopResource) {
        return nullptr;
    }

    // Получаем текстуру
    hr = desktopResource->QueryInterface(__uuidof(ID3D11Texture2D), (void**)&gpuTexture);
    if (FAILED(hr) || !gpuTexture) {
        desktopResource->Release();
        g_capture.deskDupl->ReleaseFrame();
        return nullptr;
    }
    D3D11_TEXTURE2D_DESC texDesc{};
    gpuTexture->GetDesc(&texDesc);

    // Копируем GPU → Staging (быстро)
    g_capture.d3dContext->CopyResource(g_capture.stagingTexture, gpuTexture);

    // Маппим
    D3D11_MAPPED_SUBRESOURCE mapped{};
    hr = g_capture.d3dContext->Map(g_capture.stagingTexture, 0, D3D11_MAP_READ, 0, &mapped);

    if (SUCCEEDED(hr)) {
        const size_t imageSize = static_cast<size_t>(g_capture.width) * g_capture.height * 4;
        result = env->NewByteArray(static_cast<jsize>(imageSize));

        if (result) {
            // Самый быстрый способ — получить указатель и копировать один раз
            void* dst = env->GetPrimitiveArrayCritical(result, nullptr);
            if (dst) {
                if (mapped.RowPitch == g_capture.width * 4) {
                    // Идеальный случай — один memcpy
                    memcpy(dst, mapped.pData, imageSize);
                }
                else {
                    // С padding
                    BYTE* dstPtr = static_cast<BYTE*>(dst);
                    const BYTE* srcPtr = static_cast<const BYTE*>(mapped.pData);
                    const size_t rowBytes = static_cast<size_t>(g_capture.width) * 4;

                    for (int y = 0; y < g_capture.height; ++y) {
                        memcpy(dstPtr, srcPtr, rowBytes);
                        dstPtr += rowBytes;
                        srcPtr += mapped.RowPitch;
                    }
                }
                env->ReleasePrimitiveArrayCritical(result, dst, 0);
            }
        }
    }

    // === Обязательная очистка только временных объектов ===
    gpuTexture->Release();
    desktopResource->Release();
    g_capture.deskDupl->ReleaseFrame();   // ← это важно вызывать всегда!

    return result;
}

// ====================== JNI ======================
extern "C" JNIEXPORT jbyteArray JNICALL
Java_ru_skitel_cloud_service_NativeScreenCaptureService_capture(JNIEnv* env, jobject) {
    return captureFrame(env);
}

// Опционально: метод для принудительного закрытия (если нужно)
extern "C" JNIEXPORT void JNICALL
Java_ru_skitel_cloud_service_NativeScreenCaptureService_release(JNIEnv*, jobject) {
    std::lock_guard<std::mutex> lock(g_capture.mtx);
    g_capture.releaseAll();
}