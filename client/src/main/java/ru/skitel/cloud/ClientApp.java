package ru.skitel.cloud;

public class ClientApp implements Runnable {

    public static void main(String[] args) {
        start();
    }

    public static void start()  {
        ClientModeResolver.getClientHelper().getAndSendScreenshot();
    }

    @Override
    public void run() {
        start();
    }

}
