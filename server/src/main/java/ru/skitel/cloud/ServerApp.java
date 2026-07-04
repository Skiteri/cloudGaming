package ru.skitel.cloud;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Hello world!
 */
public class ServerApp<T> implements Runnable {

    public static void main(String[] args) throws IOException {
        receiveAndDraw();
    }

    public static void start() throws IOException {
        while (true) {
            receiveAndDraw();
        }
    }


    private static void receiveAndDraw() throws IOException {

        DatagramSocket serverSocketChannel = DatagramServerFactory.getDatagramSocket();
        DatagramPacket pack = new DatagramPacket(new byte[Picture.getResolution().getWidth() * 3], Picture.getResolution().getWidth() * 3);

        serverSocketChannel.receive(pack);
        BufferedImage image = byteArrayToBufferedImage(pack.getData());
//        System.out.println(Arrays.toString(data));
//        if (data[Picture.getResolution().getWidth() * 3 - 1] == 0b1111) System.out.println(0b1111);
        draw(image);
    }

    private static void draw(byte[] x) {

    }

    private static void draw(BufferedImage bufferedImage) {
//        Drawer.drawScreen(bufferedImage);
    }

    @Override
    public void run() {
        try {
            time();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage byteArrayToBufferedImage(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void time() throws IOException {
        long prevTime = 0;
        long startTime = 0;
        boolean firstIteration = true;
        int i = 0;
        while (true) {
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

}
