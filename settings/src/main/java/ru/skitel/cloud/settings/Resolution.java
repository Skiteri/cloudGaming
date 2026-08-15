package ru.skitel.cloud.settings;

import lombok.Getter;

@Getter
public enum Resolution implements SettingSingleton {

    RESOLUTION_4K(3840, 2160),
    RESOLUTION_3K(3840, 2115),
    RESOLUTION_2K(2560, 1440),
    RESOLUTION_FULL_HD(1920, 1080),
    RESOLUTION_CUSTOM(2000, 1000);

    private final int height;
    private final int width;
    private final int pixelsCount;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
        pixelsCount = width * height;
    }

}
