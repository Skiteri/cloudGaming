package ru.skitel.cloud;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static ru.skitel.cloud.Drawer.frame;
import static ru.skitel.cloud.converter.ImageConverter.byteArrayToBufferedImage;

public class BufferedImageServerHelper extends ServerHelper<BufferedImage> {

    public void receiveAndDraw() {
        BufferedImage bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    public void drawScreen(BufferedImage bufferImage) {
        BufferedImageCanvas canvas = new BufferedImageCanvas();
        canvas.setImg(bufferImage);
        frame.setVisible(true);
        frame.add(canvas);
    }

    public BufferedImage receiveScreen() {
        try {
            DatagramServerFactory.getDatagramSocket().receive(pack);
            return byteArrayToBufferedImage(pack.getData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
