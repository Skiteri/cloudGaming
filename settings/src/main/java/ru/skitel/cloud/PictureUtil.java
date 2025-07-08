package ru.skitel.cloud;

import java.nio.ByteBuffer;

public class PictureUtil {

    public final static int FPS = 60;
    public final static int WIDTH = 3840;
    public final static int HEIGHT = 2160;
    public final static int RESOLUTION_4K = HEIGHT * WIDTH;

    public static ByteBuffer[] initEmptyBufferArray() {
        ByteBuffer[] byteBuffers = new ByteBuffer[RESOLUTION_4K];
        for (int i = 0; i < RESOLUTION_4K; i++) {
            byteBuffers[i] = ByteBuffer.allocate(3);
        }
        return byteBuffers;
    }

}
