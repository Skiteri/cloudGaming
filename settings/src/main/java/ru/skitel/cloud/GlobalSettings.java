package ru.skitel.cloud;

import ru.skitel.cloud.settings.*;

public final class GlobalSettings {

    private GlobalSettings() {
    }

    private static final Resolution RESOLUTION = Resolution.RESOLUTION_3K;
    private static final Mode SERVER_MODE = Mode.BUFFERED_IMAGE_MODE;
    private static final Mode CLIENT_MODE = Mode.INT_ARRAY_SCREEN_MODE;
    private static final ConnectionType CONNECTION_TYPE = ConnectionType.LOCAL;
    private static final PacketSettings PACKET_SETTINGS = new PacketSettings(65507);
    private static final TypeMode TYPE_MODE = TypeMode.DATAGRAM_PACKET;

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
    public static PacketSettings getPacketSettings() {
        return PACKET_SETTINGS;
    }
    public static TypeMode getTypeMode() {
        return TYPE_MODE;
    }
}
