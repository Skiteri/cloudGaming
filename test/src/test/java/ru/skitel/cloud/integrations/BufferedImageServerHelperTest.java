package ru.skitel.cloud.integrations;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.collector.ByteArrayCollectorServiceImpl;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.Drawer.frame;
import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageServerHelperTest implements ServerHelper<byte[]> {

    public void receiveAndDraw() {
        BufferedImage bufferedImage = convert(receiveScreen());
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(byte[] bufferImage) {

    }

    public void drawScreen(BufferedImage bufferImage) {
        BufferedImageCanvas canvas = new BufferedImageCanvas();
        canvas.setImg(bufferImage);
        frame.setVisible(true);
        frame.add(canvas);
    }

    @Override
    public byte[] receiveScreen() {
        ImageCollectorService<byte[]> collectorService = new ByteArrayCollectorServiceImpl();
        return collectorService.collect();
    }
}
