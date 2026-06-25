package ru.skitel.cloud;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class MyCanvas extends Canvas {

    private final static int width = 3700, height = 2180;
    private BufferedImage img;

    public MyCanvas() {
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img,0,0, null);
    }

}
