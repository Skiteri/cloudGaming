package ru.skitel.cloud;

import lombok.Getter;

@Getter
public enum Resolution {

    RESOLUTION_4k(3840, 2160),
    RESOLUTION_2K(2560, 1440),
    RESOLUTION_FULL_HD(1920, 1080);

    private final int height;
    private final int width;
    private final int pixels;

    public final static int FPS = 60;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = width * height;
    }
}
