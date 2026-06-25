package ru.skitel.cloud;

import lombok.Setter;

@Setter
public abstract class ScreenCaptureService {

    private Object screenImage;

    abstract void capture();

    protected Object getScreenImage() {
        capture();
        return screenImage;
    }

}
