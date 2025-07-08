package ru.skitel.cloud;

import lombok.Setter;

import java.awt.*;
import java.nio.ByteBuffer;

@Setter
public class Picture {

    private ByteBuffer[] pixels = new ByteBuffer[PictureUtil.RESOLUTION_4K];

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

    public ByteBuffer[] getPixels() {
        return PictureUtil.initEmptyBufferArray();
    }
}
