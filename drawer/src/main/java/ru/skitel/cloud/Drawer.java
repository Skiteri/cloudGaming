package ru.skitel.cloud;

import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;
import ru.skitel.cloud.service.ScreenCaptureService;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawer {

    public static JFrame frame = new JFrame("Рисование по точкам");

    static {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Picture.getResolution().getWidth(), Picture.getResolution().getHeight());
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        long l =  System.currentTimeMillis();

//        snapshotScreen(); // server
//        sendScreen(); // server To Client
//
//        recieveScreen();
//        drawScreen(); // client

        System.out.println(System.currentTimeMillis() - l);
        startDrawing();
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void startDrawing() {
        BufferedImage img = snapshotScreen();

        drawScreen(img);
    }

    public static void drawScreen(BufferedImage bufferImage) {
        BufferedImageCanvas canvas = new BufferedImageCanvas();
        canvas.setImg(bufferImage);
        frame.add(canvas);
    }

    public static BufferedImage snapshotScreen() {
        ScreenCaptureService screenCaptureService = new BufferedImageScreenCaptureServiceImpl();
        return  (BufferedImage) screenCaptureService.getScreenImage();
    }

}