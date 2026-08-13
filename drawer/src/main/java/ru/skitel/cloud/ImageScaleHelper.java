package ru.skitel.cloud;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageScaleHelper {
//
//    private final int monitorWidth = GlobalSettings.getResolution().getWidth();
//    private final int monitorHeight = GlobalSettings.getResolution().getHeight();
    private int scaledWidth;
    private int scaledHeight;
    private int resultWidth;
    private int resultHeight;
    private double scale;
    private BufferedImage resultImage;
    private Graphics2D g2d;

    public ImageScaleHelper() {

    }

    public void init() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        AffineTransform transform = gc.getDefaultTransform();
        double scaleX = transform.getScaleX(); // Horizontal scale
        double scaleY = transform.getScaleY(); // Vertical scale
        scaledWidth = (int) (GlobalSettings.getResolution().getWidth() /scaleX);
        scaledHeight = (int) (GlobalSettings.getResolution().getHeight() / scaleY);
        resultWidth = (int) (scaledWidth);
        resultHeight = (int) (scaledHeight);
        resultImage = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_RGB);
        g2d = resultImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    public BufferedImage scaleImage(BufferedImage screenshot) {
        g2d.drawImage(screenshot, 0, 0, resultWidth, resultHeight, null);
//        printGranisiez(g2d, resultImage.getWidth(), resultImage.getHeight());
        return resultImage;
    }

    private static void printGranisiez(Graphics2D g2d, int newW, int newH) {
        g2d.drawString(newW + " граница картинки",  newW - 100 , 100);
        g2d.drawString(newH + "  граница картинки",  100 , newH - 100);
//        g2d.drawString(gd.getDisplayMode().getWidth() + " граница экрана",  gd.getDisplayMode().getWidth() - 100 , 100);
//        g2d.drawString(gd.getDisplayMode().getHeight() + " граница экрана",  gd.getDisplayMode().getHeight() - 100 , 100);
    }

}
