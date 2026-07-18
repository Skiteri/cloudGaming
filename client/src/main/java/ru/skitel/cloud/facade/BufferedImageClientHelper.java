package ru.skitel.cloud.facade;

import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageClientHelper extends ClientHelper<BufferedImage> {

    private final BufferedImageScreenCaptureServiceImpl bufferedImageScreenCaptureService = new BufferedImageScreenCaptureServiceImpl();

    @Override
    public void getAndSendScreenshot() {
        while (true) {
            BufferedImage screenshot = bufferedImageScreenCaptureService.getScreenImage();
            sendSnapshot(screenshot);
        }
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        getChannel().write(picture);
    }

//    public void sendSnapshot(MyImage snapshot) {
//        byte[] picture = convert(snapshot);
//        getChannel().write(picture);
//    }

}
