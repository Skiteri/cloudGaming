package ru.skitel.cloud.converter;

import ru.skitel.cloud.GlobalSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MultiResolutionImage;
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

//    public static byte[] convert(BufferedImage screenshot) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(screenshot, "jpg", baos);
//            return baos.toByteArray();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static byte[] convert(BufferedImage screenshot) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convert(MultiResolutionImage mrImage) {
        // Get the best resolution variant for the requested dimensions
        Image resolutionVariant = mrImage.getResolutionVariant(GlobalSettings.getResolution().getWidth(), GlobalSettings.getResolution().getHeight());

        // Convert Image to BufferedImage
        BufferedImage bImage;
        if (resolutionVariant instanceof BufferedImage) {
            bImage = (BufferedImage) resolutionVariant;
        } else {
            bImage = new BufferedImage(
                    resolutionVariant.getWidth(null),
                    resolutionVariant.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bImage.createGraphics();
            g2d.drawImage(resolutionVariant, 0, 0, null);
            g2d.dispose();
        }

        // Write BufferedImage to byte array
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bImage, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static byte[] convert(BufferedImage screenshot) {
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//            oos.writeObject(screenshot);
//            return bos.toByteArray();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
