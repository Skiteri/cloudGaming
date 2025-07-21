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
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServerSocketChannel getSocketChannel() {
        try {
            if (serverSocketChannel == null) {
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(address);
            }
            return serverSocketChannel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
