package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.PacketSettings;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.service.datagram.DatagramImageCollectorServiceImpl;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class ByteArrayServerHelper extends ServerHelper<byte[]> {

    @Override
    public void receiveAndDraw() {
        byte[] bufferedImage = receiveScreen();
        drawScreen(bufferedImage);
    }

    @Override
    public void drawScreen(byte[] byteImage) {
        Drawer.drawScreen(convert(byteImage));
    }

    @Override
    public byte[] receiveScreen() {
        PacketSettings packetSettings = GlobalSettings.getPacketSettings();
        byte[] result = new byte[packetSettings.getDataLength()];

        ImageCollectorService collectorService = new DatagramImageCollectorServiceImpl();
        collectorService.collect(packetSettings.getIterations(), result);
        return result;
    }

//    @Override
//    public byte[] receiveScreen() {
//        ImageCollectorService collectorService = new DatagramImageCollectorServiceImpl();
//        collectorService.collect(iterations, result);
//        return result;
//    }

}
