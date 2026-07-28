package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;

import java.io.IOException;

public class ClientApp implements Runnable {

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        ClientHelper<?> clientHelper = ClientModeResolver.getClientHelper();
        int i = 0;
        while (i < 60) {
            clientHelper.getAndSendScreenshot();
            i++;
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
