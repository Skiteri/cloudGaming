package ru.skitel.cloud.service;

import ru.skitel.cloud.util.service.Capturer;
import ru.skitel.cloud.util.RobotBufferedImageCapturerImpl;

import java.awt.image.BufferedImage;

public class BufferedImageScreenCaptureService implements ScreenCaptureService<BufferedImage> {

    private final Capturer<BufferedImage> robotScreenCapture = new RobotBufferedImageCapturerImpl();
//    private final Capturer<BufferedImage> robotScreenCapture = new FastRobotBufferedImageCapturerImpl();

    @Override
    public BufferedImage getScreenImage() {
        return robotScreenCapture.capture();
    }

}
