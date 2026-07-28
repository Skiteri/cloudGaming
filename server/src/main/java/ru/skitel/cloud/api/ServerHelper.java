package ru.skitel.cloud.api;

public interface ServerHelper<T> {

    void receiveAndDraw();
    void drawScreen(T bufferImage);
    T receiveScreen();

}
