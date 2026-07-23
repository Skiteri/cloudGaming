package ru.skitel.cloud.adapter;

import ru.skitel.cloud.settings.Resolution;

import java.awt.*;

public final class ResolutionToDimAdapter {

    private ResolutionToDimAdapter(){
    }

    public static Dimension adapt(Resolution resolution) {
        return new Dimension(resolution.getWidth(), resolution.getHeight());
    }

}
