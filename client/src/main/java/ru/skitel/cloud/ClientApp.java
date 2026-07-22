package ru.skitel.cloud;

import java.io.IOException;

public class ClientApp implements Runnable {

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        ClientModeResolver.getClientHelper().getAndSendScreenshot();
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
