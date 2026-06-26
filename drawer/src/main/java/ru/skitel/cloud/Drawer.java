package ru.skitel.cloud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawer  {


    public static void main(String[] args) {

        Picture.setResolution(Resolution.RESOLUTION_4k);
        long l =  System.currentTimeMillis();

        System.out.println(System.currentTimeMillis() - l);
        draw();
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void draw() {
        MyCanvas canvas = new MyCanvas();

        BufferedImage img = ScreenCaptureServiceImpl.getInstance().get();

        JFrame frame = new JFrame("Рисование по точкам");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Picture.getResolution().getWidth(), Picture.getResolution().getHeight());
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);

        canvas.setImg(img);
        frame.add(canvas);
        frame.pack();
    }

}