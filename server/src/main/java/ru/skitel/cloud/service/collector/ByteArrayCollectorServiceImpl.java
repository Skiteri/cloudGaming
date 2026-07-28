package ru.skitel.cloud.service.collector;

import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.ReceiverHolder;
import ru.skitel.cloud.api.ImageCollectorService;
import ru.skitel.cloud.api.ReceiverService;

import java.nio.ByteBuffer;

public class ByteArrayCollectorServiceImpl implements ImageCollectorService<byte[]> {

    private final ReceiverService<byte[]> serverConnection;

    public ByteArrayCollectorServiceImpl() {
        serverConnection = ReceiverHolder.INSTANCE.getInstance();
    }

    @Override
    public byte[] collect() {
        int dataLength = ByteBuffer.wrap(serverConnection.getPack()).getInt();
        int packetLength = GlobalSettings.getPacketSettings().getPacketLength();
        int iterations = (int) Math.ceil((double) dataLength / packetLength) - 1;
        byte[] result = new byte[dataLength];

        for (int i = 0; i <= iterations; i++) {
            byte[] data = serverConnection.getPack();
            System.out.println(i);
            int length = data.length * (i + 1) > result.length ? result.length - i * data.length : data.length;
            System.arraycopy(data, 0, result, i * data.length, length);
        }
        return result;
    }
}
