package ru.skitel.cloud;


public class ClientApp implements Runnable {

    private static final ClientHelper BUFFERED_IMAGE_CLIENT_HELPER = new BufferedImageClientHelper();

    public static void main(String[] args) throws InterruptedException {
        Picture.setResolution(Resolution.RESOLUTION_FULL_HD);
//        GlobalMode.setGlobalMode(Mode.BUFFERED_IMAGE);
        start();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start() throws InterruptedException {
//      GlobalMode.getAndSendScreenshot();
        BUFFERED_IMAGE_CLIENT_HELPER.getAndSendScreenshot();
    }

}
