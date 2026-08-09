package ru.skitel.cloud.service;

import ru.skitel.cloud.ImageScaleHelper;
import ru.skitel.cloud.RobotScreenCapture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class BufferedImageScreenCaptureServiceImpl implements ScreenCaptureService {

    private final ImageScaleHelper imageScaleHelper = new ImageScaleHelper();
    private final RobotScreenCapture robotScreenCapture = new RobotScreenCapture();

    @Override
    public BufferedImage getScreenImage() {
        BufferedImage screenCapture = robotScreenCapture.getScreenshot();
        return imageScaleHelper.scaleImage(screenCapture);
    }

}
