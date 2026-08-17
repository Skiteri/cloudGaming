package ru.skitel.cloud.service;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.service.api.TransferService;

import java.io.IOException;
import java.net.DatagramPacket;

public final class DatagramChunkedTransferService implements TransferService<byte[]> {

    private final DatagramSender datagramSender = new DatagramSender();
    private final DatagramPacket datagramPacket = new DatagramPacket(new byte[0], 0, 0);

    @Override
    public void transfer(byte[] data) throws IOException, InterruptedException {
        int packetSize = GlobalSettings.getPacketSettings().getPacketLength();
        int dataLength = data.length;
        int offset = 0;

        sendDataLength(dataLength);
        for (;offset + packetSize < dataLength; offset += packetSize) {
            setAndSend(datagramPacket, data, offset, packetSize);
        }
        setAndSend(datagramPacket, data, offset, dataLength - offset);
    }

    private void setAndSend(DatagramPacket datagram, byte[] bytes, int offset, int dataLength) throws IOException, InterruptedException {
        datagram.setData(bytes, offset, dataLength);
        datagramSender.sendWithTimeout(datagram);
    }

    private void sendDataLength(int dataLength) throws IOException {
        byte[] bytes = new byte[] {
                (byte) (dataLength >>> 24),
                (byte) (dataLength >>> 16),
                (byte) (dataLength >>> 8),
                (byte) dataLength
        };
        datagramPacket.setData(bytes, 0, bytes.length);
        datagramSender.send(datagramPacket);
    }
}
