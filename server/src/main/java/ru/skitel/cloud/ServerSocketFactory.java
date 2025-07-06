package ru.skitel.cloud;

import ru.skitel.cloud.ConnectionUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketFactory {

    private static ServerSocket socket;
    private static ServerSocketChannel serverSocketChannel;
    private final static InetSocketAddress address = new InetSocketAddress(ConnectionUtil.PORT);

    public static ServerSocket getSocket() {
        try {
            if (socket == null) {
                socket = new ServerSocket(ConnectionUtil.PORT);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return socket;
    }

    public static ServerSocketChannel getSocketChannel() {
        try {
            if (serverSocketChannel == null) {
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(address);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverSocketChannel;
    }

}
