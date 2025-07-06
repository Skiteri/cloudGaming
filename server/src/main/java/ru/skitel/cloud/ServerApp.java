package ru.skitel.cloud;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Hello world!
 */
public class ServerApp {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketFactory.getSocketChannel();
        SocketChannel accept = serverSocketChannel.accept();
        long prevTime = System.currentTimeMillis();
        long workTime = prevTime;

        while (true) {
            long currTime = System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            accept.read(buffer);
            System.out.println(Arrays.toString(buffer.array()) + " время работы " + (prevTime - workTime));
            long aLong = buffer.flip().getLong();
            System.out.println("Получили ответ " + aLong + " текущее время " + currTime + " время " + (currTime - prevTime) + " мс");
            prevTime = currTime;
        }
    }
}
