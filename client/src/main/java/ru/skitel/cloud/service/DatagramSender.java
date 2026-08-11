package ru.skitel.cloud.service;

import lombok.Getter;
import ru.skitel.cloud.InetSocketAddressSingleton;
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
    public void send(DatagramPacket datagramPacket) {
        try {
            datagramSocket.send(datagramPacket);
        } catch (Exception _) {
        }
    }

    public void sendWithoutTimeout(DatagramPacket datagramPacket) {
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
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
