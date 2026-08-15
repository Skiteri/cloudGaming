package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;

import java.io.IOException;

public class ClientApp implements Runnable {

    public static void main(String[] args){
        start();
    }

    public static void start()  {
        ClientHelper<?> clientHelper = ClientModeResolver.getClientHelper();
        int i = 0;
        while (true) {
//            BenchmarkMethod.benchmarking(clientHelper::getAndSendScreenshot);
            clientHelper.getAndSendScreenshot();
        }
    }

    @Override
    public void run() {
            start();
    }

}
