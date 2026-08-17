package ru.skitel.cloud.connection;

import ru.skitel.cloud.holder.InetSocketAddressHolder;
import ru.skitel.cloud.service.api.TransferService;

import java.io.IOException;
import java.net.Socket;

@Deprecated
public class SocketConnection extends ConnectionStarter implements TransferService<byte[]> {

    private Socket socket;

    @Override
    public void transfer(byte[] bytes) {
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
            socket = new Socket(InetSocketAddressHolder.getInstance().getHostName(), InetSocketAddressHolder.getInstance().getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
