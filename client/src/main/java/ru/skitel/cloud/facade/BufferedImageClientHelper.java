package ru.skitel.cloud.facade;

import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.api.PackageWriter;
import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageClientHelper extends ClientHelper<BufferedImage> {

    private final BufferedImageScreenCaptureServiceImpl bufferedImageScreenCaptureService = new BufferedImageScreenCaptureServiceImpl();
    private final PackageWriter<byte[]> datagramPackageWriter = new DatagramPackageWriter();

    @Override
    public void getAndSendScreenshot() {
        BufferedImage screenshot = bufferedImageScreenCaptureService.getScreenImage();
        sendSnapshot(screenshot);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        try {
            datagramPackageWriter.write(picture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
