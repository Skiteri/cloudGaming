package ru.skitel.cloud.service;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.service.api.PackageWriter;
import ru.skitel.cloud.settings.PacketSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketOption;
import java.util.Arrays;

public class DatagramPackageWriter implements PackageWriter<byte[]> {

    private DatagramSender datagramSender = new DatagramSender();

//    @Override
//    public void write(byte[] data) throws IOException {
//        PacketSettings packetSettings = GlobalSettings.getPacketSettings();
//        int lastPacketCount = packetSettings.getDataLength() - packetSettings.getPacketLength() * (packetSettings.getIterations() - 1);
//        for (int i = 0; i < packetSettings.getIterations() - 1; i++) {
//            DatagramPacket datagramPacket = new DatagramPacket(data, i * packetSettings.getPacketLength(), packetSettings.getPacketLength());
//            datagramSender.send(datagramPacket);
//        }
//        DatagramPacket datagramPacket = new DatagramPacket(data, packetSettings.getIterations() * (packetSettings.getIterations() - 1), lastPacketCount);
//        datagramSender.send(datagramPacket);
//    }

//    @Override
//    public void write(byte[] data) throws IOException {
//        int packetLength = GlobalSettings.getPacketSettings().getPacketLength();
//        int dataLength = data.length;
//        sendLength(dataLength);
//        int offset = 0;
//        DatagramPacket datagramPacket = new DatagramPacket(data, offset, packetLength);
//        while (offset + packetLength < dataLength) {
//            datagramPacket = new DatagramPacket(data, offset, packetLength);
//            datagramSender.send(datagramPacket);
//            try {
//                Thread.sleep(16);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            offset += packetLength;
//            System.out.println(offset);
//        }
//        datagramPacket.setLength(dataLength - offset);
//        datagramSender.send(datagramPacket);
//
//    }
    @Override
    public void write(byte[] data) throws IOException {
        int packetLength = GlobalSettings.getPacketSettings().getPacketLength();
        int dataLength = data.length;
        sendLength(dataLength);
        int offset = 0;
        DatagramPacket datagramPacket = new DatagramPacket(data, offset, dataLength - offset);
        while (offset + packetLength < dataLength) {
            datagramPacket.setData(data, offset, packetLength);
            datagramSender.send(datagramPacket);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            offset += packetLength;
        }
        datagramPacket.setLength(dataLength - offset);
        datagramSender.send(datagramPacket);

    }

    private void sendLength(int dataLength) throws IOException {
        byte[] bytes = new byte[] {
                (byte) (dataLength >>> 24),
                (byte) (dataLength >>> 16),
                (byte) (dataLength >>> 8),
                (byte) dataLength
        };
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
        datagramSender.send(datagramPacket);
    }
}
