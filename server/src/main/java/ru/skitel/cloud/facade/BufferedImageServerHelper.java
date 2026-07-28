package ru.skitel.cloud.facade;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.service.collector.BufferedImageCollectorServiceImpl;

import java.awt.image.BufferedImage;

public class BufferedImageServerHelper implements ServerHelper<BufferedImage> {


    public void receiveAndDraw() {
        BufferedImage bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    public void drawScreen(BufferedImage bufferImage) {
        Drawer.setImage(bufferImage);
    }

    public BufferedImage receiveScreen() {
        ImageCollectorService<BufferedImage> collectorService = new BufferedImageCollectorServiceImpl();
        return collectorService.collect();
    }

}
