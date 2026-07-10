package ru.skitel.cloud;

import lombok.Getter;

@Getter
public enum GlobalSettings {

    RESOLUTION(Resolution.RESOLUTION_4k, Resolution.class),
    SERVER_MODE(Mode.BUFFERED_IMAGE_MODE, Mode.class),
    CLIENT_MODE(Mode.BUFFERED_IMAGE_MODE, Mode.class);


    private final SettingSingleton setting;
    private final Class<? extends SettingSingleton> aClass;
    //private static ProtocolMode protocolMode; todo: tcp by default. Сделать на С++

    GlobalSettings(SettingSingleton setting, Class<? extends SettingSingleton> aClass) {
        this.setting = setting;
        this.aClass = aClass;
    }
    public static Resolution getResolution() {
        return ((Resolution) RESOLUTION.getSetting());
    }


    public SettingSingleton getSetting() {
        return setting;
    }

}
