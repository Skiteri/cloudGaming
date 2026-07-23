package ru.skitel.cloud.connection;

import ru.skitel.cloud.InetSocketAddressSingleton;
import ru.skitel.cloud.service.api.PackageWriter;

import java.io.IOException;
import java.net.Socket;

public class SocketConnection extends ConnectionStarter implements PackageWriter<byte[]> {

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
            socket = new Socket(InetSocketAddressSingleton.getInstance().getHostName(), InetSocketAddressSingleton.getInstance().getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
