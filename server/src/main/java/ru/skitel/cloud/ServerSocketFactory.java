package ru.skitel.cloud;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketFactory {

    private static ServerSocket socket;

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

}
