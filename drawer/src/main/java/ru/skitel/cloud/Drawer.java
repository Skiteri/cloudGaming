package ru.skitel.cloud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Drawer {

    private static final JFrame frame = new JFrame("Рисование по точкам");
    private static final JPanel panel = new JPanel();
    private static Dimension dimension;

    static {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
    }

    public static void setImage(BufferedImage bufferImage) {
        System.out.println(bufferImage.getWidth() + " image" + bufferImage.getHeight());
        if (dimension == null) {
            dimension = new Dimension(bufferImage.getWidth(), bufferImage.getHeight());
            panel.setPreferredSize(dimension);
        }
        panel.getGraphics().drawImage(bufferImage,0,0, bufferImage.getWidth(), bufferImage.getHeight(), null);
        frame.pack();
    }

}