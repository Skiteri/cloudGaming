package ru.skitel.cloud;

import lombok.Getter;

public class PacketSettings {

    @Getter
    private final int packetLength;
    @Getter
    private final int dataLength;
    @Getter
    private final int iterations;

    public PacketSettings(int packetLength, int dataLength) {
        this.packetLength = packetLength;
        this.dataLength = dataLength;
        iterations = (int) Math.ceil((double) dataLength / packetLength);
    }
}
