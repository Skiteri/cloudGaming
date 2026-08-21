package ru.skitel.cloud.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import static ru.skitel.cloud.MonitorSettings.*;

public class ImageEncoder {

    public BufferedImage encode(int[] screenshot) {
        return interpolate(screenshot, 2);
    }

    public BufferedImage encode(byte[] screenshot) {
        return interpolate4bytePixel(screenshot, 2);
    }

    private BufferedImage interpolate(int[] screenshot, int interpolation) {
        Object pixel = null;
        for (int y = 0, posY = 0; y < DISPLAY_HEIGHT; y += interpolation, posY++) {
            for (int x = 0, posX = 0; x < DISPLAY_WIDTH; x += interpolation, posX++) {
                pixel = resultScaledImage.getColorModel().getDataElements(screenshot[DISPLAY_WIDTH * y + x], pixel);
                resultScaledImage.getRaster().setDataElements(posX, posY, pixel);
            }
        }
        IMAGE_GRAPHICS.drawImage(resultScaledImage, 0, 0, imageResultWidth, imageResultHeight, null);
        return resultScaledImage;
    }

    private BufferedImage interpolate4bytePixel(byte[] screenshot, int interpolation) {
        int mode = 4;
        if (screenshot == null || screenshot.length == 0) {
            System.out.println("❌ No data from native!");
            return null;
        }
        int offset = 0;
        int[] data = ((DataBufferInt) resultScaledImage.getRaster().getDataBuffer()).getData();
        for (int j = 0; j < imageResultHeight; j++, offset += mode * DISPLAY_WIDTH) {
            for (int i = 0; i < imageResultWidth; i++, offset += interpolation * mode) {
                int resultPixel = j * imageResultWidth + i;
                int b = screenshot[offset] & 0xFF;
                int g = screenshot[offset + 1] & 0xFF;
                int r = screenshot[offset + 2] & 0xFF;
                int a = screenshot[offset + 3] & 0xFF;

                data[resultPixel] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
        IMAGE_GRAPHICS.drawImage(resultScaledImage, 0, 0, imageResultWidth, imageResultHeight, null);
        return resultScaledImage;
    }


}
