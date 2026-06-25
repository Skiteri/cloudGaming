package ru.skitel.cloud;


import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCaptureServiceImpl extends ScreenCaptureService {

    private ScreenCaptureServiceImpl() {

    }

    private static ScreenCaptureServiceImpl screenCaptureService;

    public static ScreenCaptureServiceImpl getInstance() {
        if (screenCaptureService == null) {
            screenCaptureService = new ScreenCaptureServiceImpl();
        }
        return screenCaptureService;
    }

    public BufferedImage get() {
        return (BufferedImage) getScreenImage();
    }

    void capture() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle();
        screenRect.setSize(adapt(Resolution.RESOLUTION_4k));
        setScreenImage(robot.createScreenCapture(screenRect));
    }

    public Dimension adapt(Resolution resolution) {
        return new Dimension(resolution.getWidth(), resolution.getHeight());
    }

}
