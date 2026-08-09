package ru.skitel.cloud.service.collector;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.ImageScaleHelper;
import ru.skitel.cloud.ReceiverHolder;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ReceiverService;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageCollectorServiceImpl implements ImageCollectorService<BufferedImage> {

    private final ReceiverService<byte[]> serverConnection;
    private final ImageScaleHelper imageScaleHelper = new ImageScaleHelper();

    public BufferedImageCollectorServiceImpl() {
        serverConnection = ReceiverHolder.INSTANCE.getInstance();
    }

    public BufferedImage collect() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        int iterations = (int) Math.ceil((double) dataLength / GlobalSettings.getPacketSettings().getPacketLength()) - 1;
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        return scalingImage(convert(result));
    }

    private BufferedImage scalingImage(BufferedImage bufferedImage) {
        imageScaleHelper.init(bufferedImage.getWidth(), bufferedImage.getHeight());
        return imageScaleHelper.scaleImage(bufferedImage);
    }
}
