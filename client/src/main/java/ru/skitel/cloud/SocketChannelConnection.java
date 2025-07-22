package ru.skitel.cloud;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class SocketChannelConnection implements ClientConnectionI {

    private SocketChannel socket;

    public void init() throws RuntimeException {
        try {
            socket = SocketChannel.open();
            socket.connect(localSocketAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(Data data) {
        try {
            socket.write(data.getByteBuffer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
