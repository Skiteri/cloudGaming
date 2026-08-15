package ru.skitel.cloud;

import fastrobot.FastRobot;

import java.awt.*;

public class FastRobotIntArrayCapturerImpl implements Capturer<int[]> {

    private final FastRobot robot;
    private final Rectangle bounds ;

    public FastRobotIntArrayCapturerImpl() {
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
    public int[] capture() {
        return robot.getScreenPixels(0, 0, bounds.width, bounds.height);
    }

}
