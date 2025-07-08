package ru.skitel.cloud;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import static ru.skitel.cloud.PictureUtil.RESOLUTION_4K;
import static ru.skitel.cloud.PictureUtil.initEmptyBufferArray;

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
            ByteBuffer[] array = initEmptyBufferArray();
            array[0] = ByteBuffer.allocate(Long.BYTES);
            accept.read(array);
            System.out.println(array[0].flip().getLong() + " " + (System.currentTimeMillis() - currTime));

            System.out.println(" время работы " + (prevTime - workTime));
            System.out.println(" текущее время " + currTime + " время " + (currTime - prevTime) + " мс");
            prevTime = currTime;
        }
    }
}
