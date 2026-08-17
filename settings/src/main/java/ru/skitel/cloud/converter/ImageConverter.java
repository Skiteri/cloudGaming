package ru.skitel.cloud.converter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static ru.skitel.cloud.MonitorSettings.GRAPHICS_DEVICE;

public final class ImageConverter {

    private static final int displayHeight = GRAPHICS_DEVICE.getDisplayMode().getHeight();
    private static final int displayWidth = GRAPHICS_DEVICE.getDisplayMode().getWidth();
    private static final BufferedImage image = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_INT_RGB);

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

    public static BufferedImage convert(int[] original) {
        image.setRGB(0, 0, displayWidth, displayHeight, original, 0, displayWidth);
        return image;
    }
}
