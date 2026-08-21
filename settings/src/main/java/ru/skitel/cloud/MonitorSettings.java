package ru.skitel.cloud;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MonitorSettings {

    public static final GraphicsDevice GRAPHICS_DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int DISPLAY_HEIGHT = GRAPHICS_DEVICE.getDisplayMode().getHeight();
    public static final int DISPLAY_WIDTH = GRAPHICS_DEVICE.getDisplayMode().getWidth();
    public static int imageResultWidth = DISPLAY_WIDTH / 2;
    public static int imageResultHeight = DISPLAY_HEIGHT / 2;
    public static final BufferedImage resultScaledImage = new BufferedImage(imageResultWidth, imageResultHeight, BufferedImage.TYPE_INT_ARGB);
    public static final Graphics2D IMAGE_GRAPHICS = resultScaledImage.createGraphics();


}
