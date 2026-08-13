package ru.skitel.cloud.service.collector;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.ImageScaleHelper;
import ru.skitel.cloud.ReceiverHolder;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ReceiverService;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageCollectorServiceImpl implements ImageCollectorService<BufferedImage> {

    private final ReceiverService<byte[]> serverConnection;
    private final ImageScaleHelper imageScaleHelper = new ImageScaleHelper();
    private final AtomicInteger packetGot = new AtomicInteger(0);

    public BufferedImageCollectorServiceImpl() {
        serverConnection = ReceiverHolder.INSTANCE.getInstance();
    }

    public BufferedImage collect() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        packetGot.incrementAndGet();
        int iterations = (int) Math.ceil((double) dataLength / GlobalSettings.getPacketSettings().getPacketLength()) - 1;
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            packetGot.incrementAndGet();
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        System.out.println(packetGot.get());
        return scalingImage(convert(result));
    }

    public BufferedImage collectWithNumber() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        packetGot.incrementAndGet();
        int iterations = (int) Math.ceil((double) dataLength / GlobalSettings.getPacketSettings().getPacketLength()) - 1;
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            packetGot.incrementAndGet();
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.out.println(data[0]);
            System.arraycopy(data, 1, result, i * data.length, length - 1);
        }
        System.out.println(packetGot.get());
        return scalingImage(convert(result));
    }

    private BufferedImage scalingImage(BufferedImage bufferedImage) {
        imageScaleHelper.init(bufferedImage.getWidth(), bufferedImage.getHeight());
        return imageScaleHelper.scaleImage(bufferedImage);
    }
}
