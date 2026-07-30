package ru.skitel.cloud;

import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.setting.ServerModeSingleton;
import ru.skitel.cloud.utils.BenchmarkMethod;

public class ServerApp implements Runnable {

    public static void main(String[] arg) {
        start();
    }

    @Override
    public void run() {

    }

    private static void start() {
        ServerHelper<?> serverHelper = ServerModeSingleton.INSTANCE.getServerHelper();
        while (true) {
            BenchmarkMethod.benchmarking(serverHelper::receiveAndDraw);
        }
    }
}
