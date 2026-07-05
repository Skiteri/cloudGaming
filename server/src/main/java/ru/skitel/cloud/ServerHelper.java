package ru.skitel.cloud;

import java.net.DatagramPacket;

public abstract class ServerHelper<T> {

    public static DatagramPacket pack = new DatagramPacket(new byte[Picture.getResolution().getWidth() * 3], Picture.getResolution().getWidth() * 3);

    public abstract void receiveAndDraw();
    public abstract void drawScreen(T bufferImage);
    public abstract T receiveScreen();

}
