package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.service.*;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.utils.BenchmarkMethod;
import ru.skitel.cloud.utils.ImageEncoder;

import java.awt.image.BufferedImage;

public class BytesArrayClientHelper extends ClientHelper<byte[]> {

    private final ScreenCaptureService<byte[]> screenCaptureService = new NativeScreenCaptureService();
    private final TransferService<byte[]> datagramTransferService = new DatagramChunkedTransferService();
    private final ImageEncoder convertOriginalImageAndScale = new ImageEncoder();


    @Override
    public void getAndSendScreenshot() { // 40 ms
        byte[] screenshot = BenchmarkMethod.benchmarking(screenCaptureService::getScreenImage); // 30 ms
        BufferedImage convert = convertOriginalImageAndScale.encode(screenshot); // 100 ms -> 6 ms
        Drawer.draw(convert);

//        sendSnapshot(screenshot);

    }

    @Override
    public void sendSnapshot(byte[] snapshot) {
//        getChannel().transfer(snapshot);
    }

}
