package ru.skitel.cloud;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.nio.ByteBuffer;

import static ru.skitel.cloud.Resolution.RESOLUTION_4k;

@Setter
public class Picture {

    private ByteBuffer[] pixels = new ByteBuffer[RESOLUTION_4k.getPixels()];
    @Getter
    private static Resolution resolution;

    public static void setResolution(Resolution resolution) {
        Picture.resolution = resolution;
    }

    public static int getPictureSize() {
        return resolution.getPixels();
    }

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

    public byte[][] getPixelsByte() {
        return PictureUtil.initEmptyByteArray();
    }
}
