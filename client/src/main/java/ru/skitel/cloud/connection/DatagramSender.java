package ru.skitel.cloud.connection;

import ru.skitel.cloud.InetSockedAddressFactory;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.PacketSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramSender extends ClientConnector implements PackageWriter<byte[]> {

    private DatagramSocket datagramSocket;

    @Override
    public void write(byte[] bytes) throws IOException {
        PacketSettings packetSettings = GlobalSettings.getPacketSettings();
        int lastPacketCount = packetSettings.getDataLength() - packetSettings.getPacketLength() * (packetSettings.getIterations() - 1);
        for (int i = 0; i < packetSettings.getIterations() - 1; i++) {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, i * packetSettings.getPacketLength(), packetSettings.getPacketLength());
            datagramSocket.send(datagramPacket);
        }

        DatagramPacket datagramPacket = new DatagramPacket(bytes, packetSettings.getIterations() * (packetSettings.getIterations() - 1), lastPacketCount);
        datagramSocket.send(datagramPacket);
    }

    @Override
    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetSockedAddressFactory.getInstance());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
