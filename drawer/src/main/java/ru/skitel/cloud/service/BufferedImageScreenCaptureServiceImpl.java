package ru.skitel.cloud.service;

import ru.skitel.cloud.FastRobotScreenCapture;
import ru.skitel.cloud.RobotScreenCapture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class BufferedImageScreenCaptureServiceImpl implements ScreenCaptureService {

//    private final RobotScreenCapture robotScreenCapture = new RobotScreenCapture();
    private final FastRobotScreenCapture robotScreenCapture = new FastRobotScreenCapture();

    @Override
    public BufferedImage getScreenImage() {
        return robotScreenCapture.getScreenshot();
    }
}
