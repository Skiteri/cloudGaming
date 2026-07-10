package ru.skitel.cloud;

import ru.skitel.cloud.facade.ServerHelper;

/**
 * Hello world! todo: 3 на 3 и Settings и mode buffer(можно обьединить)
 */
public class ServerApp implements Runnable {

    public static void main(String[] args) {
        ServerModeResolver.getServerHelper().receiveAndDraw();
    }

    @Override
    public void run() {

    }

    private static void time(ServerHelper serverHelper) {
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
