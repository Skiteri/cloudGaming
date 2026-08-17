package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.utils.ImageEncoder;
import ru.skitel.cloud.service.DatagramChunkedTransferService;
import ru.skitel.cloud.service.IntArrayScreenCaptureService;
import ru.skitel.cloud.service.ScreenCaptureService;
import ru.skitel.cloud.service.api.TransferService;

import java.awt.image.BufferedImage;

public class IntArrayClientHelper extends ClientHelper<int[]> {

    private final ScreenCaptureService<int[]> screenCaptureService = new IntArrayScreenCaptureService();
    private final TransferService<byte[]> datagramTransferService = new DatagramChunkedTransferService();
    private final ImageEncoder convertOriginalImageAndScale = new ImageEncoder();

    @Override
    public void getAndSendScreenshot() { //180 ms -> 70ms
        int[] screenshot = screenCaptureService.getScreenImage(); //60 ms // 8294400 int

        BufferedImage convert = convertOriginalImageAndScale.encode(screenshot); // 100 ms -> 6 ms
        Drawer.draw(convert); //3 ms

//      BenchmarkMethod.benchmarking(() -> sendSnapshot(screenshot));
    }

    @Override
    public void sendSnapshot(int[] snapshot) {

    }
}
