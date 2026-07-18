package ru.skitel.cloud.facade;

import ru.skitel.cloud.Drawer;
import ru.skitel.cloud.api.ServerHelper;

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
//        try {
//            return getServerConnection().getPack();
            return null;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
