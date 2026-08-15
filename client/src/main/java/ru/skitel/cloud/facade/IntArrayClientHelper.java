package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.ImageIntScaleHelper;
import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.IntArrayScreenCaptureService;
import ru.skitel.cloud.service.ScreenCaptureService;
import ru.skitel.cloud.service.api.PackageWriter;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.awt.image.BufferedImage;

public class IntArrayClientHelper extends ClientHelper<int[]> {

    private final ScreenCaptureService<int[]> screenCaptureService = new IntArrayScreenCaptureService();
    private final PackageWriter<byte[]> datagramPackageWriter = new DatagramPackageWriter();
    private final ImageIntScaleHelper convertOriginalImageAndScale = new ImageIntScaleHelper();

    @Override
    public void getAndSendScreenshot() { //103 ms
        int[] screenshot = screenCaptureService.getScreenImage(); //60 ms // 8294400 int
        BufferedImage convert = convertOriginalImageAndScale.convert(screenshot) ; // 100 ms
        Drawer.setImage(convert); //3 ms

//      BenchmarkMethod.benchmarking(() -> sendSnapshot(screenshot));
    }

    @Override
    public void sendSnapshot(int[] snapshot) {

    }
}
