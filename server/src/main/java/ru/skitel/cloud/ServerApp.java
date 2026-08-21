package ru.skitel.cloud;

import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.setting.ServerModeSingleton;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;

public class ServerApp implements Runnable {

    public static void main(String[] arg) throws IOException, InterruptedException {
        start();
    }

    @Override
    public void run() {

    }

    private static void start() throws IOException, InterruptedException {
        ServerHelper<?> serverHelper = ServerModeSingleton.INSTANCE.getServerHelper();
        while (true) {
            BenchmarkMethod.benchmarking(serverHelper::receiveAndDraw);
        }
    }
}
