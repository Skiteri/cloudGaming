package ru.skitel.cloud;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class DrawerTest {

    public static void main(String... args) {

        int width = 3, height = 3;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width * 200, height * 200);
        frame.setLocationRelativeTo(null);

        Drawer.setFrame(frame);

        BufferedImageCanvas canvas = new BufferedImageCanvas();
        BufferedImage square = new BufferedImage(width * 200, height * 200 ,1);
        Graphics graphics = square.getGraphics();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color((int) (Math.random() * 0x1000000));
                graphics.fillRect(i  * 200, j  * 200, 200, 200);
                graphics.setColor(color);
            }
        }
        canvas.setImg(square);
        frame.add(canvas);

        frame.setVisible(true);
    }

}