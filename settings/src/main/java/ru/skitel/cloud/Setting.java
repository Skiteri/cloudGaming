package ru.skitel.cloud;

import lombok.Getter;

public final class Setting {

    static {
        Setting.setResolution(Resolution.RESOLUTION_4k);
    }

    @Getter
    private static Resolution resolution;

    @Getter
    private static ServerMode serverMode;

    //private static ProtocolMode protocolMode; todo: tcp by default. Сделать на С++

    public static void setResolution(Resolution resolution) {
        if (Setting.resolution != null) throw new RuntimeException("Уже инициализировано разрешение картинки");
        Setting.resolution = resolution;
    }

    public static void setServerMode(ServerMode serverMode) {
        if (Setting.serverMode != null) throw new RuntimeException("Уже инициализировано мод передачи данных");
        Setting.serverMode = serverMode;
    }

}
