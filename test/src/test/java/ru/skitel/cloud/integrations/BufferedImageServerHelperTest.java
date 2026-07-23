package ru.skitel.cloud.integrations;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.datagram.DatagramImageCollectorServiceImpl;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.Drawer.frame;
import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageServerHelperTest extends ServerHelper<byte[]> {

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
        byte[] result = new byte[GlobalSettings.getPacketSettings().getDataLength()];

        ImageCollectorService collectorService = new DatagramImageCollectorServiceImpl();
        collectorService.collect();
        return result;
    }
}
