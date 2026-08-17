package ru.skitel.cloud.util;

import fastrobot.FastRobot;
import ru.skitel.cloud.util.service.Capturer;

import java.awt.*;

import static ru.skitel.cloud.MonitorSettings.GRAPHICS_DEVICE;

public class FastRobotIntArrayCapturerImpl implements Capturer<int[]> {

    private final FastRobot robot;
    private final Rectangle bounds ;

    public FastRobotIntArrayCapturerImpl() {
        try {
            int tempH = GRAPHICS_DEVICE.getDisplayMode().getHeight();
            int tempW = GRAPHICS_DEVICE.getDisplayMode().getWidth();
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
