package ru.skitel.cloud;

import java.net.InetSocketAddress;

public final class InetSockedAddressFactory {

    private InetSockedAddressFactory() {
    }

    private static final InetSocketAddress inetSocketAddress;

    static {
        ConnectionType connectionType = GlobalSettings.getConnectionType();
        inetSocketAddress = new InetSocketAddress(connectionType.getHostname(), connectionType.getPort());
    }

    public static InetSocketAddress getInstance() {
        return inetSocketAddress;
    }

}
