package ru.skitel.cloud;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientApp {

    public static void main(String[] args) {
        Picture.setResolution(Resolution.RESOLUTION_4k);
        SocketChannel channel = ClientConnection.getInstance();
        for (int i = 0; i < 1; i++) {
            try {
                Picture images = new Picture();
                ByteBuffer[] src = images.getPixels();
                System.out.println(ObjectSizeFetcher.getObjectSize(src));
//                if (i == 0) {
//                    src[0] = ByteBuffer.allocate(Long.BYTES).putLong(System.currentTimeMillis()).flip();
//                    System.out.println(Arrays.toString(src[0].array()));
//                }
                channel.write(src);
//                Thread.sleep(ClientConnection.TIMEOUT - 4);
            } catch (IOException e ) {
                throw new RuntimeException(e);
            }
        }
    }

}
