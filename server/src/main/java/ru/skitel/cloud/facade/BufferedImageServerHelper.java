package ru.skitel.cloud.facade;

import ru.skitel.cloud.BufferedImageCanvas;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.datagram.DatagramImageCollectorServiceImpl;
import ru.skitel.cloud.api.ImageCollectorService;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.Drawer.frame;
import static ru.skitel.cloud.converter.ImageConverter.convert;

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
        int maxPacketLength = 65507;
        int length = GlobalSettings.getResolution().getPixelsCount() * 3;
        int iterations = (int) Math.ceil((double) length / maxPacketLength);

        byte[] result = new byte[length];

        ImageCollectorService collectorService = new DatagramImageCollectorServiceImpl();
        collectorService.collect(iterations, result);
        return convert(result);
    }


}
