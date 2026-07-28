package ru.skitel.cloud.settings;

import lombok.Getter;

public class PacketSettings {

    @Getter
    private final int packetLength;

    public PacketSettings(int packetLength) {
        this.packetLength = packetLength;
    }

}
