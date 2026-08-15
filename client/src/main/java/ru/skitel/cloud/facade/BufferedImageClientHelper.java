package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.utils.ImageResizer;
import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.ScreenCaptureService;
import ru.skitel.cloud.service.api.PackageWriter;
import ru.skitel.cloud.service.BufferedImageScreenCaptureService;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageClientHelper extends ClientHelper<BufferedImage> {

    private final ScreenCaptureService<BufferedImage> bufferedImageScreenCaptureService = new BufferedImageScreenCaptureService();
    private final PackageWriter<byte[]> datagramPackageWriter = new DatagramPackageWriter();
    private final ImageResizer imageResizer = new ImageResizer();

    @Override
    public void getAndSendScreenshot() {
        BufferedImage screenshot = bufferedImageScreenCaptureService.getScreenImage();  // 100 ms
        BufferedImage resize = imageResizer.resize(screenshot); //33 ms
        Drawer.setImage(resize); //3 ms
//        BenchmarkMethod.benchmarking(() -> sendSnapshot(screenshot));
    }


    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        datagramPackageWriter.write(picture);
    }
}
