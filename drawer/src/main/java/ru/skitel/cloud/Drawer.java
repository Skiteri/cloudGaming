package ru.skitel.cloud;

import lombok.Setter;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Drawer {

    @Setter
    public static JFrame frame = new JFrame("Рисование по точкам");

    static {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Picture.getResolution().getWidth(), Picture.getResolution().getHeight());
        frame.setLocationRelativeTo(null); // Center on screen
    }

    public static void drawScreen(BufferedImage bufferedImage) {
        BufferedImageCanvas canvas = new BufferedImageCanvas();
        canvas.setImg(bufferedImage);
        frame.setVisible(true);
        frame.add(canvas);
    }

}