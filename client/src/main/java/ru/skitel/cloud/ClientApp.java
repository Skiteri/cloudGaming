package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;

public class ClientApp implements Runnable {

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start()  {
        ClientHelper<?> clientHelper = ClientModeResolver.getClientHelper();
        while (true) {
            BenchmarkMethod.benchmarking(clientHelper::getAndSendScreenshot);
        }
    }

    @Override
    public void run() {
            start();
    }

}
