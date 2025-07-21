package ru.skitel.cloud;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static ru.skitel.cloud.PictureUtil.*;

/**
 * Hello world!
 */
public class ServerApp {

    public static void main(String[] args) throws IOException {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        ServerSocketChannel serverSocketChannel = ServerSocketFactory.getSocketChannel();
        SocketChannel accept = serverSocketChannel.accept();
        long prevTime = System.currentTimeMillis();
        long workTime = prevTime;
        int i = 0;
        while (true) {

            long currTime = System.currentTimeMillis();
            ByteBuffer[] array = initEmptyBufferArray();
            accept.read(array);
//            System.out.println(array[0].flip().getLong() + " " + (System.currentTimeMillis() - currTime));
//            if (i == HEIGHT) {
//                System.out.println(" время работы " + (prevTime - workTime));
//                System.out.println(" текущее время " + currTime + " время " + (currTime - prevTime) + " мс");
//            }
            prevTime = currTime;
            i++;
        }
    }
}
