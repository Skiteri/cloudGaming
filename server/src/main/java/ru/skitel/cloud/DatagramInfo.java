package ru.skitel.cloud;

import ru.skitel.cloud.settings.PacketSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramInfo {

    private final DatagramPacket pack;
    private final DatagramSocket socket;

    public DatagramInfo() throws SocketException {
        PacketSettings packetSettings = GlobalSettings.getPacketSettings();
        pack = new DatagramPacket(new byte[packetSettings.getPacketLength()], packetSettings.getPacketLength());
        socket = new DatagramSocket(GlobalSettings.getConnectionType().getPort());
    }

    public void receive() {
        try {
            socket.receive(pack);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getData() {
        return pack.getData();
    }
}