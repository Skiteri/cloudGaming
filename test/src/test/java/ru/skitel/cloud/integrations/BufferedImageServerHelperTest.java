package ru.skitel.cloud.integrations;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.collector.BufferedImageCollectorServiceImpl;

import java.awt.image.BufferedImage;


public class BufferedImageServerHelperTest implements ServerHelper<BufferedImage> {

    @Override
    public void receiveAndDraw() {
        BufferedImage bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(BufferedImage bufferImage) {
        Drawer.setImage(bufferImage);
    }

    @Override
    public BufferedImage receiveScreen() {
        ImageCollectorService<BufferedImage> collectorService = new BufferedImageCollectorServiceImpl();
        return collectorService.collect();
    }
}
