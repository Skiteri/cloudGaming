package ru.skitel.cloud.rubbish;

import lombok.Setter;

import java.awt.*;
import java.nio.ByteBuffer;


@Setter
public class Picture {

    private ByteBuffer[] pixels = new ByteBuffer[1];

    public static ByteBuffer getColor(int x, int y) {
        ByteBuffer allocate = ByteBuffer.allocate(3);
        try {
            Robot robot = new Robot();
            Color pixelColor = robot.getPixelColor(x, y);
            allocate.put((byte) pixelColor.getRGB());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return allocate;
    }

}
