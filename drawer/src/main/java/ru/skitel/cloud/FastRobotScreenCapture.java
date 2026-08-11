package ru.skitel.cloud;

import fastrobot.FastRobot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FastRobotScreenCapture {

    private final FastRobot robot;
    private final Rectangle bounds ;

    public FastRobotScreenCapture() {
        try {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int tempH = gd.getDisplayMode().getHeight();
            int tempW = gd.getDisplayMode().getWidth();
            robot = new FastRobot();
            bounds = new Rectangle(tempW, tempH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getScreenshot() {
        return robot.createScreenCapture(bounds);
    }
}
