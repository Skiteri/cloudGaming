package ru.skitel.cloud;

import lombok.Setter;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Drawer {

    @Setter
    public static JFrame frame = new JFrame("Рисование по точкам");
    static BufferedImageCanvas canvas = new BufferedImageCanvas();


    static {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GlobalSettings.getResolution().getWidth(), GlobalSettings.getResolution().getHeight());
        frame.setLocationRelativeTo(null); // Center on screen
        frame.add(canvas);
    }

    public static void setImage(BufferedImage bufferImage) {
        canvas.setImg(bufferImage);
        canvas.repaint();
    }

}