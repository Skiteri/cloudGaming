package ru.skitel.cloud.service.datagram;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.PacketSettings;
import ru.skitel.cloud.api.Receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServerReceiver implements Receiver<byte[]> {

    private final DatagramInfo datagramInfo;

    private DatagramServerReceiver(DatagramInfo datagramInfo) {
        this.datagramInfo = datagramInfo;
    }

    @Override
    public byte[] getPack() {
        datagramInfo.receive();
        return datagramInfo.getData();
    }

    public static final class DatagramSocketFactory {
        static {
            try {
                datagramServerReceiver = new DatagramServerReceiver(new DatagramInfo());
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
        }
        private static final DatagramServerReceiver datagramServerReceiver;


        public static DatagramServerReceiver getInstance() {
            return datagramServerReceiver;
        }

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
