package ru.skitel.cloud.service.datagram;

import ru.skitel.cloud.DatagramInfo;
import ru.skitel.cloud.api.Receiver;

import java.net.SocketException;

public class ByteArrayReceiver<T> implements Receiver<T> {

    private final DatagramInfo datagramInfo;

    public ByteArrayReceiver() {
        try {
            this.datagramInfo = new DatagramInfo();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getPack() {
        datagramInfo.receive();
        return (T) datagramInfo.getData();
    }

}
