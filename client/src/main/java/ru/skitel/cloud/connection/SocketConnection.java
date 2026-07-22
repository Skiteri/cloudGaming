package ru.skitel.cloud.connection;

import ru.skitel.cloud.InetSockedAddressFactory;

import java.io.IOException;
import java.net.Socket;

public class SocketConnection extends ClientConnector implements PackageWriter<byte[]> {

    private Socket socket;

    @Override
    public void write(byte[] bytes) {
        try {
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void openConnection() {
        try {
            socket = new Socket(InetSockedAddressFactory.getInstance().getHostName(), InetSockedAddressFactory.getInstance().getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
