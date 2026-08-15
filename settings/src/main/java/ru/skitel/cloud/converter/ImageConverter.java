package ru.skitel.cloud.converter;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ImageConverter {

    private ImageConverter(){
    }

    public static BufferedImage convert(byte[] bytes) {
        try (InputStream ios = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(ios);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convert(BufferedImage screenshot) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(screenshot, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
