package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.ImageScaleHelper;
import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.api.PackageWriter;
import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageClientHelper extends ClientHelper<BufferedImage> {

    private final BufferedImageScreenCaptureServiceImpl bufferedImageScreenCaptureService = new BufferedImageScreenCaptureServiceImpl();
    private final PackageWriter<byte[]> datagramPackageWriter = new DatagramPackageWriter();
    private final ImageScaleHelper imageScaleHelper = new ImageScaleHelper();

    @Override
    public void getAndSendScreenshot() {
//        BufferedImage screenshot = bufferedImageScreenCaptureService.getScreenImage();
        BufferedImage screenshot = BenchmarkMethod.benchmarking(bufferedImageScreenCaptureService::getScreenImage);
        Drawer.setImage(scalingImage(screenshot));
//        sendSnapshot(screenshot);
    }

    private BufferedImage scalingImage(BufferedImage bufferedImage) {
        imageScaleHelper.init();
        return imageScaleHelper.scaleImage(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        datagramPackageWriter.write(picture);
    }
}
