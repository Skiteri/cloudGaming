package ru.skitel.cloud.service;


import ru.skitel.cloud.GlobalSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.skitel.cloud.adapter.ResolutionToDimAdapter.adapt;

public class BufferedImageScreenCaptureServiceImpl implements ScreenCaptureService {

    @Override
    public BufferedImage getScreenImage() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle();
        screenRect.setSize(adapt(GlobalSettings.getResolution()));
        return robot.createScreenCapture(screenRect);
    }


}
