package ru.skitel.cloud;

import ru.skitel.cloud.utils.BenchmarkMethod;
import ru.skitel.cloud.utils.ImageResizer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageIntScaleHelper {

    private final OriginalImage originalImage = new OriginalImage();
    private final ImageResizer adaptedMonitorImage = new ImageResizer() ;

    public BufferedImage convert(int[] screenshot) {
        BufferedImage original4k = originalImage.convertImage(screenshot); //20 ms
        return adaptedMonitorImage.resize(original4k); //20 ms
    }

    private static class OriginalImage {
        private final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        private final int displayHeight = gd.getDisplayMode().getHeight();
        private final int displayWidth = gd.getDisplayMode().getWidth();
        private final BufferedImage resultScaledImage = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_INT_RGB);

        private BufferedImage convertImage(int[] original) {
            resultScaledImage.setRGB(0, 0, displayWidth, displayHeight, original, 0, displayWidth);
            return resultScaledImage;
        }
    }

}
