package ru.skitel.cloud;


public class ClientApp implements Runnable {

    public static void main(String[] args) throws InterruptedException  {
        Picture.setResolution(Resolution.RESOLUTION_4k);
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
        DatagramConnection channel = (DatagramConnection) ClientConnectionI.connect(new DatagramConnection());
        for (int frame = 0; frame < 1; frame++) {
            byte[][] picture = new Picture().getPixelsByte();
            for (int i = 0; i < picture.length; i++) {
                channel.write(picture[i]);
                System.out.println(i);
                timeout(i);
            }
        }
    }

    private static void timeout(int frame) {
        if (frame % 140 != 0) {
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
