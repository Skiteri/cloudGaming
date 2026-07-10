package ru.skitel.cloud.connection;

import ru.skitel.cloud.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramConnection implements ClientConnectionI {

    private DatagramSocket datagramSocket;

    @Override
    public void write(Data data) {
        byte[] bytes = data.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(byte[] bytes) {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws IOException {
        datagramSocket = new DatagramSocket();
        datagramSocket.connect(localSocketAddress);
    }
}
