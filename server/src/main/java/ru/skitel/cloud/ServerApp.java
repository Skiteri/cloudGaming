package ru.skitel.cloud;

import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.setting.ServerModeSingleton;

public class ServerApp implements Runnable {

    public static void main(String[] arg) {
        long startTime = System.currentTimeMillis();
        ServerHelper<?> serverHelper = ServerModeSingleton.INSTANCE.getServerHelper();
        int i = 0;
        while (true) {
            serverHelper.receiveAndDraw();
            i++;
        }
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
