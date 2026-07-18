package ru.skitel.cloud.api;

import lombok.Getter;
import ru.skitel.cloud.service.datagram.DatagramServerReceiver;

public abstract class ServerHelper<T> {

    @Getter
    private final Receiver receiver = DatagramServerReceiver.DatagramSocketFactory.getInstance();

    public abstract void receiveAndDraw();
    public abstract void drawScreen(T bufferImage);
    public abstract T receiveScreen();

}
