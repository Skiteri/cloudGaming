package ru.skitel.cloud.service.datagram;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.settings.PacketSettings;
import ru.skitel.cloud.api.ReceiverService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ByteArrayReceiverService<T> implements ReceiverService<T> {

    private final DatagramInfo datagramInfo;

    public ByteArrayReceiverService() {
        try {
            this.datagramInfo = new DatagramInfo();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getPack() {
        datagramInfo.receive();
        return (T) datagramInfo.getData();
    }

    private static class DatagramInfo {

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

}
