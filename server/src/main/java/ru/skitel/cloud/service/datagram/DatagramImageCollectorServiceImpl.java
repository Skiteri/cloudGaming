package ru.skitel.cloud.service.datagram;

import ru.skitel.cloud.api.ImageCollectorService;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class DatagramImageCollectorServiceImpl implements ImageCollectorService {

    private final DatagramServerReceiver serverConnection = DatagramServerReceiver.DatagramSocketFactory.getInstance();

    public BufferedImage collect(int countSending, byte[] result) {
        for (int i = 0; i <= countSending - 1; i++) {
            byte[] data = serverConnection.getPack();
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        return convert(result);
    }
}
