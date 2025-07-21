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

}
