package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.collector.BufferedImageCollectorServiceImpl;

import java.awt.image.BufferedImage;

public class BufferedImageServerHelper implements ServerHelper<BufferedImage> {

    public void receiveAndDraw() {
        BufferedImage bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    public void drawScreen(BufferedImage bufferImage) {
        Drawer.draw(bufferImage);
    }

    public BufferedImage receiveScreen() {
        BufferedImageCollectorServiceImpl collectorService = new BufferedImageCollectorServiceImpl();
        return collectorService.collect();
    }

}
