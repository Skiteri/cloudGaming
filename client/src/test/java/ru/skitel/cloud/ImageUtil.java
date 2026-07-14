package ru.skitel.cloud;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {

    public static BufferedImage create3by3() {
        int width = 3, height = 3;

        Color[] colors = new Color[9];

        colors[0] = Color.red;
        colors[1] = Color.blue;
        colors[2] = Color.black;
        colors[3] = Color.yellow;
        colors[4] = Color.gray;
        colors[5] = Color.green;
        colors[6] = Color.white;
        colors[7] = Color.magenta;
        colors[8] = Color.orange;

        BufferedImage square = new BufferedImage(width * 200, height * 200 ,1);
        Graphics graphics = square.getGraphics();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = colors[i * 3 + j];
                graphics.setColor(color);
                graphics.fillRect(i  * 200, j  * 200, 200, 200);
            }
        }
        return square;
    }

}
