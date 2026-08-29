package ru.skitel.cloud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

public class Drawer {

    private static final JFrame frame = new JFrame("Рисование по точкам");
    private static final JPanel panel = new JPanel();
    private static Dimension dimension;
    private final AtomicInteger imagesDrew = new AtomicInteger();
    private final AtomicInteger imagesNotDrew = new AtomicInteger();
    private static final Drawer drawer = new Drawer();

    static {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
    }

    public static void draw(BufferedImage bufferImage) {
        if (bufferImage == null) {
            return;
        }
        if (dimension == null) {
            dimension = new Dimension(bufferImage.getWidth(), bufferImage.getHeight());
            panel.setPreferredSize(dimension);
        }
        panel.getGraphics().drawImage(bufferImage,0,0, bufferImage.getWidth(), bufferImage.getHeight(), null);
        panel.getGraphics().dispose();
        frame.pack();
        drawer.imagesDrew.incrementAndGet();
    }

    public synchronized static int getImagesDrew() {
        return drawer.imagesDrew.get();
    }

    public synchronized static AtomicInteger getImagesNotDrew() {
        return drawer.imagesNotDrew;
    }
}