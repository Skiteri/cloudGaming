package ru.skitel.cloud;

import java.io.IOException;

import static ru.skitel.cloud.converter.ImageConverter.byteArrayToBufferedImage;

public class ByteArrayServerHelper extends ServerHelper<byte[]> {

    @Override
    public void receiveAndDraw() {
        byte[] bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(byte[] byteImage) {
        Drawer.drawScreen(byteArrayToBufferedImage(byteImage));
    }

    @Override
    public byte[] receiveScreen() {
        try {
            DatagramServerFactory.getDatagramSocket().receive(pack);
            return pack.getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
