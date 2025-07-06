package ru.skitel.cloud;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ClientApp {

    public static void main(String[] args) {
        SocketChannel channel = ClientConnection.getInstance();
        for (int i = 0; i < 60; i++){
            try {
                ByteBuffer src = ByteBuffer.allocate(Long.BYTES).putLong(System.currentTimeMillis()).flip();
                channel.write(src);
                System.out.println(Arrays.toString(src.flip().array()) + "  " + src.getLong());
                Thread.sleep(ClientConnection.TIMEOUT - 4);
            } catch (IOException|InterruptedException e ) {
                throw new RuntimeException(e);
            }
        }
    }

}
