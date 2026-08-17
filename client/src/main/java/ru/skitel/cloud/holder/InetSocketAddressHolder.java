package ru.skitel.cloud.holder;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.settings.ConnectionType;

import java.net.InetSocketAddress;

public enum InetSocketAddressHolder {

    INSTANCE;

    private final InetSocketAddress inetSocketAddress;

    InetSocketAddressHolder() {
        ConnectionType connectionType = GlobalSettings.getConnectionType();
        this.inetSocketAddress = new InetSocketAddress(connectionType.getHostname(), connectionType.getPort());
    }

    public static InetSocketAddress getInstance() {
        return INSTANCE.inetSocketAddress;
    }

}
