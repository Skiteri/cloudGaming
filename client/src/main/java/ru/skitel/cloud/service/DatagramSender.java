package ru.skitel.cloud.service;

import lombok.Getter;
import ru.skitel.cloud.InetSocketAddressSingleton;
import ru.skitel.cloud.connection.ConnectionStarter;
import ru.skitel.cloud.service.api.PackageSender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;

public class DatagramSender extends ConnectionStarter implements PackageSender<DatagramPacket> {

    @Getter
    private DatagramSocket datagramSocket;
    private AtomicInteger packetSent = new AtomicInteger(0);

    public DatagramSender() {
        start();
    }

    @Override
    public void send(DatagramPacket datagramPacket) {
        try {
            datagramSocket.send(datagramPacket);
            Thread.sleep(1);
            System.out.println(packetSent.incrementAndGet());
        } catch (Exception _) {
        }

    }

    public void sendWithoutTimeout(DatagramPacket datagramPacket) {
        try {
            datagramSocket.send(datagramPacket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetSocketAddressSingleton.getInstance());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

}
