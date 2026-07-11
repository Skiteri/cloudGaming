package ru.skitel.cloud;

import java.net.InetSocketAddress;

public final class ClientConnectionSingleton {

    private static final InetSocketAddress inetSocketAddress;

    static {
        ConnectionType connectionType = GlobalSettings.getConnectionType();
        inetSocketAddress = new InetSocketAddress(connectionType.getHostname(), connectionType.getPort());
    }

    private ClientConnectionSingleton() {
    }

    public static InetSocketAddress getInstance() {
        return inetSocketAddress;
    }

}
