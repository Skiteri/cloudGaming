package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.red;
import static ru.skitel.cloud.converter.ImageConverter.convert;

public class ClientHelperTest extends ClientHelper<BufferedImage> {

    @Override
    public void getAndSendScreenshot() {
        BufferedImage bufferedImage = create3by3();
        sendSnapshot(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        getChannel().write(picture);
    }

    private BufferedImage create3by3() {
        int width = 3, height = 3;

        BufferedImage square = new BufferedImage(width * 200, height * 200 ,1);
        Graphics graphics = square.getGraphics();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = red;
                graphics.fillRect(i  * 200, j  * 200, 200, 200);
                graphics.setColor(color);
            }
        }
        return square;
    }


}