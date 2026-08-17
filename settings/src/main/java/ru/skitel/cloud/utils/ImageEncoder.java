package ru.skitel.cloud.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import static ru.skitel.cloud.MonitorSettings.*;

public class ImageEncoder {

    public BufferedImage encode(int[] screenshot) {
        return interpolate(screenshot, 2);
    }

    private BufferedImage interpolate(int[] screenshot, int interpolation) {
        Object pixel = null;
        for (int y = 0, posY = 0; y < DISPLAY_HEIGHT; y += interpolation, posY++) {
            for (int x = 0, posX = 0; x < DISPLAY_WIDTH; x += interpolation, posX++) {
                pixel = ColorModel.getRGBdefault().getDataElements(screenshot[DISPLAY_WIDTH * y + x], pixel);
                resultScaledImage.getRaster().setDataElements(posX, posY, pixel);
            }
        }
        IMAGE_GRAPHICS.drawImage(resultScaledImage, 0, 0, imageResultWidth, imageResultHeight, null);
        return resultScaledImage;
    }

}
