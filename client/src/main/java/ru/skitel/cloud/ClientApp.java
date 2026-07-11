package ru.skitel.cloud;

public class ClientApp implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        start();
    }

    public static void start() throws InterruptedException {
        ClientModeResolver.getClientHelper().getAndSendScreenshot();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
