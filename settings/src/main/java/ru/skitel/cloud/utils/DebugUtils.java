package ru.skitel.cloud.utils;

import java.awt.*;

public class DebugUtils {
    public static void printGranisiez(Graphics2D g2d, int newW, int newH) {
        g2d.drawString(newW + " граница картинки",  newW - 100 , 100);
        g2d.drawString(newH + "  граница картинки",  100 , newH - 100);
//        g2d.drawString(gd.getDisplayMode().getWidth() + " граница экрана",  gd.getDisplayMode().getWidth() - 100 , 100);
//        g2d.drawString(gd.getDisplayMode().getHeight() + " граница экрана",  gd.getDisplayMode().getHeight() - 100 , 100);
    }
}
