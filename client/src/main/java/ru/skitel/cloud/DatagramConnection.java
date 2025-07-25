package ru.skitel.cloud;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class DatagramConnection implements ClientConnectionI {

    private DatagramSocket datagramSocket;

    @Override
    public void write(Data data) {
        byte[] bytes = data.getBytes();
        System.out.println(Arrays.toString(bytes));
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 3);
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
