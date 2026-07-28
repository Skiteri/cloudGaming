package ru.skitel.cloud.service.collector;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.ReceiverHolder;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.Receiver;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageCollectorServiceImpl implements ImageCollectorService<BufferedImage> {

    private final Receiver<byte[]> serverConnection;

    public BufferedImageCollectorServiceImpl(Receiver<byte[]> datagramServerReceiver) {
        this.serverConnection = datagramServerReceiver;
    }

    public BufferedImageCollectorServiceImpl() {
        serverConnection = ReceiverHolder.INSTANCE.getInstance();
    }

    public BufferedImage collect() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        int iterations = (int) Math.ceil((double) dataLength / GlobalSettings.getPacketSettings().getPacketLength()) - 1;
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            System.out.println(i);
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        return convert(result);
    }

}
