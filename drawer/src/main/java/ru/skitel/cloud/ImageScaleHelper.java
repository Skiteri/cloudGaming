package ru.skitel.cloud;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScaleHelper {

    private final int monitorWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    private final int monitorHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    private final int scaledWidth;
    private final int scaledHeight;
    private final int resultWidth;
    private final int resultHeight;
    private final double scale;
    private final BufferedImage resultImage;
    private final Graphics2D g2d;
    private final Rectangle screenRect;

    public ImageScaleHelper() {
        screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        
        scaledWidth = GlobalSettings.getResolution().getWidth() * screenRect.width / monitorWidth;
        scaledHeight = GlobalSettings.getResolution().getHeight() * screenRect.height / monitorHeight;
        scale = Math.min((double) scaledWidth / screenRect.getWidth(), (double) scaledHeight / screenRect.getHeight());
        resultWidth = (int) (screenRect.getWidth() * scale);
        resultHeight = (int) (screenRect.getHeight() * scale);
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
