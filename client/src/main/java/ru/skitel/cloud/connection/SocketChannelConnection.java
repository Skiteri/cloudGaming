package ru.skitel.cloud.connection;

import ru.skitel.cloud.ClientConnectionSingleton;
import ru.skitel.cloud.ClientConnector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelConnection extends ClientConnector implements ClientConnectionI<ByteBuffer> {

    private SocketChannel socket;

    @Override
    public void openConnection() throws RuntimeException {
        try {
            socket = SocketChannel.open();
            socket.connect(ClientConnectionSingleton.getInstance());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(ByteBuffer data) {
        try {
            socket.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
