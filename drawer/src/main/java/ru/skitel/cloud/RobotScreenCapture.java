package ru.skitel.cloud;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RobotScreenCapture {

    private final Robot robot;

    public RobotScreenCapture() {
        try  {
            robot = new Robot();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getScreenshot() {
        return robot.createScreenCapture(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds());
    }
}
