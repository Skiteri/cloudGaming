package ru.skitel.cloud;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

/**
 * Hello world!
 */
public class ServerApp {

    public static void main(String[] args) throws IOException {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        ServerSocket serverSocketChannel = ServerSocketFactory.getSocket();
        long prevTime = System.currentTimeMillis();
        long workTime = prevTime;
        int i = 0;
        while (true) {
            long currTime = System.currentTimeMillis();
            byte[] bytes = serverSocketChannel.accept().getInputStream().readAllBytes();
            System.out.println(Arrays.toString(bytes));
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
