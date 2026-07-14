package ru.skitel.cloud.facade;

import java.net.DatagramPacket;

public abstract class ServerHelper<T> {

    public static DatagramPacket pack = new DatagramPacket(new byte[8400], 8400); //todo: hardcoded

    public abstract void receiveAndDraw();
    public abstract void drawScreen(T bufferImage);
    public abstract T receiveScreen();

}
