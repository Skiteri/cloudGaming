package ru.skitel.cloud.service;

import ru.skitel.cloud.FastRobotIntArrayCapturerImpl;

public class IntArrayScreenCaptureService implements ScreenCaptureService<int[]> {

    private final FastRobotIntArrayCapturerImpl robotScreenCapture = new FastRobotIntArrayCapturerImpl();

    @Override
    public int[] getScreenImage() {
        return robotScreenCapture.capture(); //60 ms
    }

}
