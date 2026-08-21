package ru.skitel.cloud.service;

import ru.skitel.cloud.DatagramInfo;
import ru.skitel.cloud.api.Receiver;

import java.net.SocketException;

public class IntArrayReceiver implements Receiver<int[]> {

    private final DatagramInfo datagramInfo;

    public IntArrayReceiver() {
        try {
            this.datagramInfo = new DatagramInfo();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] getPack() {
        datagramInfo.receive();
        byte[] data = datagramInfo.getData();
        return null;
    }
}
