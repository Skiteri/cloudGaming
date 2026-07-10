package ru.skitel.cloud.converter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ImageConverter {

    private ImageConverter(){
    }

    public static BufferedImage convert(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convert(BufferedImage screenshot) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
