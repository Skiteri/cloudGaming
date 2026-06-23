package ru.skitel.cloud;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Hello world!
 */
public class ServerApp implements Runnable {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void time() throws IOException {
        long prevTime = 0;
        long startTime = 0;
        boolean firstIteration = true;
        int i = 0;
        while (startTimer()) {
            receiveAndDraw();
            if (firstIteration) {
                startTime = System.currentTimeMillis();
                firstIteration = false;
            }
            System.out.println(i);
            if (i++ == Picture.getResolution().getHeight()) {
                System.out.println(
                        " Время получения пакетов " + (System.currentTimeMillis() - startTime) +
                                " Время между пакетов " + (System.currentTimeMillis() - prevTime));
            }
//            System.out.println(60 * Picture.getResolution().getHeight());
            prevTime = System.currentTimeMillis();
        }
    }

    public static void start() throws IOException {
        while (startTimer()) {
            receiveAndDraw();
        }
    }

    private static boolean startTimer() {
        return true;
    }

    private static void receiveAndDraw() throws IOException {
        DatagramSocket serverSocketChannel = DatagramServerFactory.getDatagramSocket();
        DatagramPacket pack = new DatagramPacket(new byte[Picture.getResolution().getWidth() * 3], Picture.getResolution().getWidth() * 3);
        serverSocketChannel.receive(pack);
        byte[] data = pack.getData();
//        System.out.println(Arrays.toString(data));
//        if (data[Picture.getResolution().getWidth() * 3 - 1] == 0b1111) System.out.println(0b1111);
        draw(data);
    }

    private static void draw(byte[] x) {

    }

    @Override
    public void run() {
        try {
            time();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
