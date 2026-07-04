package ru.skitel.cloud;

import ru.skitel.cloud.service.BufferedImageScreenCaptureServiceImpl;
import ru.skitel.cloud.service.ByteArrayScreenCaptureServiceImpl;
import ru.skitel.cloud.service.ScreenCaptureService;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Drawer {

    public static JFrame frame = new JFrame("Рисование по точкам");

    static {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Picture.getResolution().getWidth(), Picture.getResolution().getHeight());
        frame.setLocationRelativeTo(null); // Center on screen
    }


}