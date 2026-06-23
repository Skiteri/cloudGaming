package ru.skitel.cloud;

import java.nio.ByteBuffer;

public class PictureUtil {

    public static ByteBuffer[] initEmptyBufferArray() {
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        for (int i = 0; i < 3; i++) {
            byteBuffers[i] = ByteBuffer.allocate(
                    Picture.getResolution().getHeight() *
                            Picture.getResolution().getWidth());
        }
        return byteBuffers;
    }

    public static byte[][] initEmptyByteArray() {
        byte[][] bytes = new byte[Picture.getResolution().getHeight()][Picture.getResolution().getWidth() * 3];
        for (int i = 0; i < bytes.length; i++) {
            for (int s = 0; s < bytes[0].length; s++) {
                if (i == 0 && s == 0) bytes[i][s] = 0b111;
                if (i == bytes.length - 1 && bytes[0].length - 1 == 0) bytes[i][s] = 0b1111;
                bytes[i][s] = 0b011;
            }
        }
        return bytes;
    }

    public static byte[][] numeratedByteArray() {
        byte[][] bytes = new byte[Picture.getResolution().getHeight()][Picture.getResolution().getWidth() * 3];
        for (int i = 0; i < bytes.length; i++) {
            for (int s = 0; s < bytes[0].length; s++) {
                bytes[i][s] = (byte) (i * s);
            }
        }
        return bytes;
    }
}
