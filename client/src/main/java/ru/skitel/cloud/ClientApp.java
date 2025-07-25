package ru.skitel.cloud;


public class ClientApp {

    public static void main(String[] args) {

        Picture.setResolution(Resolution.RESOLUTION_4k);
        ClientConnectionI channel = ClientConnectionI.connect(new DatagramConnection());
//        ClientConnectionI channel = ClientConnectionI.connect(new SocketChannelConnection());

        Picture images = new Picture();
        byte[][] src = images.getPixelsByte();
        for (byte[] a : src) {
            channel.write(new Data(a));
        }
    }

}
