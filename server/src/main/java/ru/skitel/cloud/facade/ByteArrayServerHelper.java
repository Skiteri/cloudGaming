package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.collector.ByteArrayCollectorServiceImpl;
import ru.skitel.cloud.utils.ImageEncoder;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class ByteArrayServerHelper implements ServerHelper<byte[]> {

    private final ImageEncoder convertOriginalImageAndScale = new ImageEncoder();

    @Override
    public void receiveAndDraw() {
        byte[] bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(byte[] byteImage) {
        BufferedImage convert = convertOriginalImageAndScale.encode(byteImage); // 100 ms -> 6 ms
        Drawer.draw(convert);
    }

    @Override
    public byte[] receiveScreen() {
        ImageCollectorService<byte[]> collectorService = new ByteArrayCollectorServiceImpl();
        return collectorService.collect();
    }

}
