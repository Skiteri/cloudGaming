package ru.skitel.cloud;

import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        byte[] picture = snapshotToByteArray(snapshot);
        getChannel().write(picture);
    }


    private static byte[] snapshotToByteArray(BufferedImage screenshot) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
