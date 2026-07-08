package ru.skitel.cloud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.skitel.cloud.BufferedImageClientHelper.snapshotToByteArray;

public class ClientHelperTest extends ClientHelper<BufferedImage> {

    @Override
    public void getAndSendScreenshot() {
        BufferedImage bufferedImage = create3by3();
        sendSnapshot(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = snapshotToByteArray(snapshot);
        getChannel().write(picture);
    }


    private BufferedImage create3by3() {
        int width = 3, height = 3;

        BufferedImage square = new BufferedImage(width * 200, height * 200 ,1);
        Graphics graphics = square.getGraphics();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color((int) (Math.random() * 0x1000000));
                graphics.fillRect(i  * 200, j  * 200, 200, 200);
                graphics.setColor(color);
            }
        }
        return square;
    }


}