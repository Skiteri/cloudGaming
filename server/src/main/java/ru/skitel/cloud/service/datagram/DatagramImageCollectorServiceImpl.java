package ru.skitel.cloud.service.datagram;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.api.ImageCollectorService;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class DatagramImageCollectorServiceImpl implements ImageCollectorService {

    private final DatagramServerReceiver serverConnection;

    public DatagramImageCollectorServiceImpl(DatagramServerReceiver datagramServerReceiver) {
        this.serverConnection = datagramServerReceiver;
    }

    public DatagramImageCollectorServiceImpl() {
        serverConnection = DatagramServerReceiver.DatagramSocketFactory.getInstance();
    }


    public BufferedImage collect() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        int iterations = (int) Math.ceil((double) dataLength / GlobalSettings.getPacketSettings().getPacketLength()) - 1;
        System.out.println(dataLength + " " + iterations);
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            System.out.println(i);
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        return convert(result);
    }

//    public BufferedImage collect() {
//        byte[] result = new byte[100000];
//        int receiveBufferSize = serverConnection.getReceiveBufferSize();
//        byte[] data = serverConnection.getPack();
//        System.arraycopy(data, 0, result, data.length, data.length);
//        return convert(result);
//    }
}
