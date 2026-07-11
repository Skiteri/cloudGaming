package ru.skitel.cloud;

public final class GlobalSettings {

    private GlobalSettings() {
    }

    private static final Resolution RESOLUTION = Resolution.RESOLUTION_4k;
    private static final Mode SERVER_MODE = Mode.BUFFERED_IMAGE_MODE;
    private static final Mode CLIENT_MODE = Mode.BUFFERED_IMAGE_MODE;
    //private static ProtocolMode protocolMode; todo: tcp by default. Сделать на С++


    public static Resolution getResolution() {
        return RESOLUTION;
    }

    public static Mode getClientMode() {
        return CLIENT_MODE;
    }

    public static Mode getServerMode() {
        return SERVER_MODE;
    }
}
