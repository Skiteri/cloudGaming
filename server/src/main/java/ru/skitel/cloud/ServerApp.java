package ru.skitel.cloud;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Hello world!
 */
public class ServerApp {

    public static void main(String[] args) throws IOException {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        DatagramSocket serverSocketChannel = DatagramServerFactory.getDatagramSocket();
//        ServerSocket serverSocketChannel = ServerSocketFactory.getSocket();
        int i = 1;
        time();
        long currTime = System.currentTimeMillis();
        while (true) {
//            byte[] bytes = serverSocketChannel.accept().getInputStream().readAllBytes();
            i++;
            long time = time();
            if (i % (Picture.getResolution().getWidth() - 1) == 0) {
                System.out.println((time - currTime) + " pakect " + (i % 60));
            }
        }
    }

    public static long time() throws IOException {
        DatagramSocket serverSocketChannel = DatagramServerFactory.getDatagramSocket();
        DatagramPacket pack = new DatagramPacket(new byte[Picture.getResolution().getHeight() * 3], Picture.getResolution().getHeight() * 3);
        serverSocketChannel.receive(pack);
        byte[] bytes = pack.getData();
        return System.currentTimeMillis();
    }
}
