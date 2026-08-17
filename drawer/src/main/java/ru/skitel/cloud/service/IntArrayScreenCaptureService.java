package ru.skitel.cloud.service;

import ru.skitel.cloud.util.FastRobotIntArrayCapturerImpl;
import ru.skitel.cloud.util.service.Capturer;

public class IntArrayScreenCaptureService implements ScreenCaptureService<int[]> {

    private final Capturer<int[]> robotScreenCapture = new FastRobotIntArrayCapturerImpl();

    @Override
    public int[] getScreenImage() {
        return robotScreenCapture.capture(); //60 ms
    }

}
