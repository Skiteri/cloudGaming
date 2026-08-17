package ru.skitel.cloud.connection;

import ru.skitel.cloud.holder.InetSocketAddressHolder;
import ru.skitel.cloud.service.api.TransferService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Deprecated
public class SocketChannelConnection extends ConnectionStarter implements TransferService<ByteBuffer> {

    private SocketChannel socket;

    @Override
    public void openConnection() throws RuntimeException {
        try {
            socket = SocketChannel.open();
            socket.connect(InetSocketAddressHolder.getInstance());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transfer(ByteBuffer data) {
        try {
            socket.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
