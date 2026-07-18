package ru.skitel.cloud;

import java.awt.*;
import java.nio.ByteBuffer;

public class PictureUtil {

    public static ByteBuffer[] initEmptyBufferArray() {
        return null;
    }

    public static byte[][] initEmptyByteArray() {
        return null;
    }

    public static byte[][] numeratedByteArray() {
        byte[][] bytes = new byte[GlobalSettings.getResolution().getHeight()][GlobalSettings.getResolution().getWidth() * 3];
        for (int i = 0; i < bytes.length; i++) {
            for (int s = 0; s < bytes[0].length; s++) {
                bytes[i][s] = (byte) (i * s);
            }
        }
        return bytes;
    }

    public static byte[] randomByteArray() {
        byte[] bytes = new byte[GlobalSettings.getResolution().getHeight() * GlobalSettings.getResolution().getWidth()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (Math.random() * 256);
        }
        return bytes;
    }
}
