package ru.skitel.cloud.facade;

import ru.skitel.cloud.GlobalSettings;

import java.net.DatagramPacket;

public abstract class ServerHelper<T> {

    public static DatagramPacket pack = new DatagramPacket(new byte[GlobalSettings.getResolution().getWidth() * 3], GlobalSettings.getResolution().getWidth() * 3);

    public abstract void receiveAndDraw();
    public abstract void drawScreen(T bufferImage);
    public abstract T receiveScreen();

}
