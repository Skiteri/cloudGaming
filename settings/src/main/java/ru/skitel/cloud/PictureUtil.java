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
        byte[][] bytes = new byte[Picture.getResolution().getHeight() *
                Picture.getResolution().getWidth()][3];
        for (int i = 0, s = 0; i < 3; i++) {
            for (; s < bytes.length; s++) {
                bytes[s][i] = 0b0100;
            }
        }
        return bytes;
    }

}
