package ru.skitel.cloud;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.skitel.cloud.adapter.ResolutionToDimAdapter.adapt;

@Getter
@Setter
public class BufferedImageCanvas extends Canvas {

    private BufferedImage img;

    public BufferedImageCanvas() {
        setSize(adapt(Picture.getResolution()));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img,0,0, null);
    }

}
