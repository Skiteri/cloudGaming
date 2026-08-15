package ru.skitel.cloud.service;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.service.api.PackageWriter;

import java.net.DatagramPacket;

public class DatagramPackageWriter implements PackageWriter<byte[]> {

    private final DatagramSender datagramSender = new DatagramSender();

    @Override
    public void write(byte[] data) {
        int packetLength = GlobalSettings.getPacketSettings().getPacketLength();
        int dataLength = data.length;
        int offset = 0;
        final DatagramPacket datagramPacket = new DatagramPacket(data, offset, 0);

        sendLength(dataLength);
        for (;offset + packetLength < dataLength; offset += packetLength) {
            setAndSend(datagramPacket, data, offset, packetLength);
        }
        setAndSend(datagramPacket, data, offset, dataLength - offset);
    }

    private void setAndSend(DatagramPacket datagram, byte[] bytes, int offset, int dataLength) {
        datagram.setData(bytes, offset, dataLength);
        datagramSender.send(datagram);
    }

    private void sendLength(int dataLength) {
        byte[] bytes = new byte[] {
                (byte) (dataLength >>> 24),
                (byte) (dataLength >>> 16),
                (byte) (dataLength >>> 8),
                (byte) dataLength
        };
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
        datagramSender.sendWithoutTimeout(datagramPacket);
    }
}
