package ru.skitel.cloud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawer  {

    public static int width = 3700, height = 2180;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - l);
        draw();
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void draw() {
        MyCanvas canvas = new MyCanvas();

        BufferedImage img = ScreenCaptureServiceImpl.getInstance().get();

        JFrame frame = new JFrame("Рисование по точкам");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);

        canvas.setImg(img);
        frame.add(canvas);
        frame.pack();
    }

}