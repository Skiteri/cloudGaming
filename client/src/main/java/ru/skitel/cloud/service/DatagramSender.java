package ru.skitel.cloud.service;

import lombok.Getter;
import ru.skitel.cloud.holder.InetSocketAddressHolder;
import ru.skitel.cloud.connection.ConnectionStarter;
import ru.skitel.cloud.service.api.PackageSender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramSender extends ConnectionStarter implements PackageSender<DatagramPacket> {

    @Getter
    private DatagramSocket datagramSocket;

    public DatagramSender() {
        start();
    }

    @Override
    public void send(DatagramPacket datagramPacket) throws IOException {
        datagramSocket.send(datagramPacket);
    }

    public void sendWithTimeout(DatagramPacket datagramPacket) throws InterruptedException, IOException {
        datagramSocket.send(datagramPacket);
        Thread.sleep(1);
    }

    @Override
    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetSocketAddressHolder.getInstance());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

}
