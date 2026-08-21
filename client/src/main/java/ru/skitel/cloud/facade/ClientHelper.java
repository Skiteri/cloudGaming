package ru.skitel.cloud.facade;

import java.io.IOException;

public abstract class ClientHelper<T>  {

    public abstract void getAndSendScreenshot() throws IOException, InterruptedException;
    public abstract void sendSnapshot(T snapshot) throws IOException, InterruptedException;

}
