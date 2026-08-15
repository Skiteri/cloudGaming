package ru.skitel.cloud;

import fastrobot.FastRobot;

import java.awt.*;
import java.awt.image.BufferedImage;

@Deprecated
public class FastRobotBufferedImageCapturerImpl implements Capturer<BufferedImage> {

    private final FastRobot robot;
    private final Rectangle bounds ;

    public FastRobotBufferedImageCapturerImpl() {
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

    @Override
    public BufferedImage capture() {
        return robot.createScreenCapture(bounds);
    }
}
