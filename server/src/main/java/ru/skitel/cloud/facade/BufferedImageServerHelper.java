package ru.skitel.cloud.facade;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.datagram.DatagramImageCollectorServiceImpl;
import ru.skitel.cloud.api.ImageCollectorService;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.Drawer.frame;

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
//        int length = GlobalSettings.getPacketSettings().getDataLength();

        ImageCollectorService collectorService = new DatagramImageCollectorServiceImpl();
        return collectorService.collect();
    }


}
