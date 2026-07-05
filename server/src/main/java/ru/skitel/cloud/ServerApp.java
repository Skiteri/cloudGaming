package ru.skitel.cloud;

import java.io.IOException;

/**
 * Hello world! todo: 3 на 3 и Settings и mode buffer(можно обьединить)
 */
public class ServerApp implements Runnable {

    public static ServerHelper serverHelper = new BufferedImageServerHelper();

    public static void main(String[] args) {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        serverHelper.receiveAndDraw();
    }

    @Override
    public void run() {
        try {
            time();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void time() throws IOException {
        long prevTime = 0;
        while (true) {
            long startTime = System.currentTimeMillis();
            serverHelper.receiveAndDraw();
            System.out.println("Время получения пакетов " + (System.currentTimeMillis() - startTime) +
                               " Время между пакетов "    + (System.currentTimeMillis() - prevTime));
            prevTime = System.currentTimeMillis();
        }
    }
}
