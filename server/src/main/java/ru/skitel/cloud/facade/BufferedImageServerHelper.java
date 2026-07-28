package ru.skitel.cloud.facade;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.service.collector.BufferedImageCollectorServiceImpl;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.Drawer.frame;

public class BufferedImageServerHelper implements ServerHelper<BufferedImage> {

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
        ImageCollectorService<BufferedImage> collectorService = new BufferedImageCollectorServiceImpl();
        return collectorService.collect();
    }

}
