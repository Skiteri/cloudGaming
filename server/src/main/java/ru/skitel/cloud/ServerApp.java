package ru.skitel.cloud;

import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.setting.ServerModeSingleton;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;

public class ServerApp implements Runnable {

    public static void main(String[] arg) {
        try  {
            start();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

    }

    private static void start() throws IOException, InterruptedException {
        ServerHelper<?> serverHelper = ServerModeSingleton.INSTANCE.getServerHelper();
        int i = 0;
        while (true) {
//        while (i < 5) {
//            BenchmarkMethod.benchmarking(serverHelper::receiveAndDraw) ;
            serverHelper.receiveAndDraw();
            i++;
        }
    }
}
