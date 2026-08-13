package ru.skitel.cloud.settings;

import lombok.Getter;

@Getter
public enum ConnectionType {

    LOCAL("localhost", 8080),
    WI_FI("192.168.0.25", 5643);

    private final String hostname;
    private final int port;

    ConnectionType(String host, int port) {
        this.hostname = host;
        this.port = port;
    }

}
