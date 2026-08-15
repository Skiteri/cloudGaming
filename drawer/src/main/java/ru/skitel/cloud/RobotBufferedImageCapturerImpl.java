package ru.skitel.cloud;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RobotBufferedImageCapturerImpl implements Capturer<BufferedImage> {

    private final Robot robot;
    private final Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();

    public RobotBufferedImageCapturerImpl() {
        try  {
            robot = new Robot();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage capture() {
        return robot.createScreenCapture(bounds);
    }
}
