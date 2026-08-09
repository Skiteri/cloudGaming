package ru.skitel.cloud.service;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.service.api.PackageWriter;

import java.io.IOException;
import java.net.DatagramPacket;

public class DatagramPackageWriter implements PackageWriter<byte[]> {

    private final DatagramSender datagramSender = new DatagramSender();

    @Override
    public void write(byte[] data) throws IOException {
        int packetLength = GlobalSettings.getPacketSettings().getPacketLength();
        int dataLength = data.length;
        int offset = 0;
        final DatagramPacket datagramPacket = new DatagramPacket(data, offset, dataLength - offset);

        sendLength(dataLength);
        while (offset + packetLength < dataLength) {
            datagramPacket.setData(data, offset, packetLength);
            datagramSender.send(datagramPacket);
            offset += packetLength;
        }
        datagramPacket.setLength(dataLength - offset);
        datagramSender.send(datagramPacket);
    }

    private void sendLength(int dataLength) {
        byte[] bytes = new byte[]{
                (byte) (dataLength >>> 24),
                (byte) (dataLength >>> 16),
                (byte) (dataLength >>> 8),
                (byte) dataLength
        };
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
        datagramSender.sendWithoutTimeout(datagramPacket);
    }
}
