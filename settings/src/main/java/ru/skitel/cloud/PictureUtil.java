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
        byte[][] bytes = new byte[Picture.getResolution().getHeight() * 3][Picture.getResolution().getHeight() * 3];
        for (int i = 0; i < bytes.length; i++) {
            for (int s = 0; s < bytes[0].length; s++) {
                bytes[i][s] = 0b111;
            }
        }
        return bytes;
    }

}
