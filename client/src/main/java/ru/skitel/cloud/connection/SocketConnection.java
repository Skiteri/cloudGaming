package ru.skitel.cloud.connection;

import ru.skitel.cloud.Data;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class SocketConnection implements ClientConnectionI {

    private Socket socket;

    @Override
    public void write(Data data) {
        try {
            byte[] bytes = data.getBytes();
            System.out.println(Arrays.toString(bytes));
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws IOException {
        socket = new Socket(socketAddress.getHostName(), socketAddress.getPort());
    }
}
