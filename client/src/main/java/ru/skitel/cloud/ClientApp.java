package ru.skitel.cloud;


public class ClientApp {

    public static void main(String[] args) {

        Picture.setResolution(Resolution.RESOLUTION_4k);
        ClientConnectionI channel = ClientConnectionI.connect(new DatagramConnection());
        for (int frame = 0; frame < 60; frame++) {
            byte[][] picture = new Picture().getPixelsByte();
            for (byte[] a : picture) {
                channel.write(new Data(a));
            }
        }
    }

}
