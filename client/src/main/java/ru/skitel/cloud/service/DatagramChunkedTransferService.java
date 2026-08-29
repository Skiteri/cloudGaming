package ru.skitel.cloud.service;

import lombok.Getter;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.concurrent.atomic.AtomicInteger;

public final class DatagramChunkedTransferService implements TransferService<byte[]> {

    private final DatagramSender datagramSender = new DatagramSender();
    private final DatagramPacket datagramPacket = new DatagramPacket(new byte[0], 0, 0);
    @Getter

    private static final AtomicInteger packetDrop = new AtomicInteger(0);
    @Getter

    private static final AtomicInteger packetSend = new AtomicInteger(0);


    @Override
    public void transfer(byte[] data) throws IOException, InterruptedException {
        if (data == null) {
            packetDrop.getAndIncrement();
            return;
        }

        int packetSize = GlobalSettings.getPacketSettings().getPacketLength();
        int dataLength = data.length;
        int offset = 0;

        sendDataLength(dataLength);
        for (;offset + packetSize < dataLength; offset += packetSize) {
            setAndSend(datagramPacket, data, offset, packetSize);
        }
        setAndSend(datagramPacket, data, offset, dataLength - offset);
        packetSend.getAndIncrement();
    }

    private void setAndSend(DatagramPacket datagram, byte[] bytes, int offset, int dataLength) throws IOException, InterruptedException {
        datagram.setData(bytes, offset, dataLength);
//        BenchmarkMethod.benchmarking(() -> datagramSender.send(datagram));
        datagramSender.send(datagram);
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
