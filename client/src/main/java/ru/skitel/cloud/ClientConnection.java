package ru.skitel.cloud;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SocketChannel;

import static ru.skitel.cloud.Resolution.FPS;


public class ClientConnection {

    private final static SocketChannel socket;
    private final static InetSocketAddress inetSocketAddress = new InetSocketAddress(ConnectionUtil.HOST, ConnectionUtil.PORT);
    private final static InetSocketAddress localSocketAddress = new InetSocketAddress(ConnectionUtil.LOCALHOST, ConnectionUtil.PORT);
    public final static long TIMEOUT = 1000 / FPS;

    static {
        try {
            socket = SocketChannel.open();
            socket.connect(localSocketAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static SocketChannel getInstance() {
        return socket;
    }

}
