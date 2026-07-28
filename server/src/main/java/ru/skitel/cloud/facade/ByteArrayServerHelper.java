package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.collector.ByteArrayCollectorServiceImpl;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class ByteArrayServerHelper implements ServerHelper<byte[]> {

    @Override
    public void receiveAndDraw() {
        byte[] bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(byte[] byteImage) {
//        Drawer.drawScreen(convert(byteImage));
    }

    @Override
    public byte[] receiveScreen() {
        ImageCollectorService<byte[]> collectorService = new ByteArrayCollectorServiceImpl();
        return collectorService.collect();
    }

}
