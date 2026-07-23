package ru.skitel.cloud;

import ru.skitel.cloud.settings.ConnectionType;

import java.net.InetSocketAddress;

public enum InetSocketAddressSingleton {

    INSTANCE;

    private final InetSocketAddress inetSocketAddress;

    InetSocketAddressSingleton() {
        ConnectionType connectionType = GlobalSettings.getConnectionType();
        this.inetSocketAddress = new InetSocketAddress(connectionType.getHostname(), connectionType.getPort());
    }

    public static InetSocketAddress getInstance() {
        return INSTANCE.inetSocketAddress;
    }

}
