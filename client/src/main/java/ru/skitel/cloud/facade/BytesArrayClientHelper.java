package ru.skitel.cloud.facade;

import ru.skitel.cloud.service.*;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;

public class BytesArrayClientHelper extends ClientHelper<byte[]> {

    private final ScreenCaptureService<byte[]> screenCaptureService = new NativeScreenCaptureService();
    private final TransferService<byte[]> datagramTransferService = new DatagramChunkedTransferService();


    @Override
    public void getAndSendScreenshot() throws IOException, InterruptedException { // 40 ms
        byte[] screenshot = screenCaptureService.getScreenImage(); // 30 ms
        sendSnapshot(screenshot);
    }

    @Override
    public void sendSnapshot(byte[] snapshot) throws IOException, InterruptedException {
//         datagramTransferService.transfer(snapshot);
        datagramTransferService.transfer(snapshot);
    }

}
