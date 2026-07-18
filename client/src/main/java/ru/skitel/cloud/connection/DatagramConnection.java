package ru.skitel.cloud.connection;

import ru.skitel.cloud.ClientConnectionSingleton;
import ru.skitel.cloud.ClientConnector;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.Picture;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramConnection extends ClientConnector implements ClientConnectionI<byte[]> {

    private DatagramSocket datagramSocket;

    @Override
    public void write(byte[] bytes) {
        int length = GlobalSettings.getResolution().getPixelsCount() * 3;
        int maxPacketLength = 65507;
        int countSending = (int) Math.ceil((double) length / 65507);
        try {
            for (int i = 0; i < countSending; i++) {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, i, maxPacketLength);
                datagramSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(ClientConnectionSingleton.getInstance());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
