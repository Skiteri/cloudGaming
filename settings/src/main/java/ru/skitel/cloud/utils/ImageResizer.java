package ru.skitel.cloud.utils;

import ru.skitel.cloud.GlobalSettings;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageResizer {

    private final int resultWidth;
    private final int resultHeight;
    private final BufferedImage resultScaledImage;
    private final Graphics2D g2d;

    public ImageResizer() {
        AffineTransform transform = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .getDefaultTransform();
        resultWidth = (int) (GlobalSettings.getResolution().getWidth() / transform.getScaleX());
        resultHeight = (int) (GlobalSettings.getResolution().getHeight() / transform.getScaleY());
        resultScaledImage = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_ARGB);

        g2d = resultScaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    public BufferedImage resize(BufferedImage screenshot) {
        g2d.drawImage(screenshot, 0, 0, resultWidth, resultHeight, null);
//        printGranisiez(g2d, resultImage.getWidth(), resultImage.getHeight());
        return resultScaledImage;
    }

}
