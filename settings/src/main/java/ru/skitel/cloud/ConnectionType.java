package ru.skitel.cloud;

import lombok.Getter;

@Getter
public enum ConnectionType {

    LOCAL(ConnectionUtil.LOCALHOST, ConnectionUtil.PORT),
    WI_FI(ConnectionUtil.HOST, ConnectionUtil.PORT);

    private final String hostname;
    private final int port;

    ConnectionType(String host, int port) {
        this.hostname = host;
        this.port = port;
    }

}
