package ru.skitel.cloud;

import lombok.Setter;
import ru.skitel.cloud.settings.ConnectionType;
import ru.skitel.cloud.settings.Mode;
import ru.skitel.cloud.settings.PacketSettings;
import ru.skitel.cloud.settings.Resolution;

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
    @Setter
    private static PacketSettings PACKET_SETTINGS = new PacketSettings(65507, 1); //todo: откуда брать длину пакета?

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
    public static PacketSettings getPacketSettings() {return PACKET_SETTINGS;
    }
}
