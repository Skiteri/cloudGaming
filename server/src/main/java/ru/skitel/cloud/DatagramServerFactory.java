package ru.skitel.cloud;

import java.io.IOException;
import java.net.DatagramSocket;

public class DatagramServerFactory {

    private static DatagramSocket socket;

    public static DatagramSocket getDatagramSocket() {
        try {
            if (socket == null) {
                socket = new DatagramSocket(ConnectionUtil.PORT);
            }
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
