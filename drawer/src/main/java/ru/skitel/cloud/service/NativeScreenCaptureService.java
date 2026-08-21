package ru.skitel.cloud.service;

public class NativeScreenCaptureService implements ScreenCaptureService<byte[]> {

    // Объявление нативной функции
    public native byte[] capture();
    public native void release();

    @Override
    public byte[] getScreenImage() {
        return capture();
    }

}
