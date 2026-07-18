package ru.skitel.cloud;

import lombok.Setter;

public final class GlobalSettings {

    private GlobalSettings() {
    }

    @Setter
    private static Resolution RESOLUTION = Resolution.RESOLUTION_4k;
    @Setter
    private static Mode SERVER_MODE = Mode.BUFFERED_IMAGE_MODE;
    @Setter
    private static Mode CLIENT_MODE = Mode.BUFFERED_IMAGE_MODE;
    @Setter
    private static ConnectionType CONNECTION_TYPE = ConnectionType.LOCAL;
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
    public static ConnectionType getConnectionType() {
        return CONNECTION_TYPE;
    }

}
