package ru.skitel.cloud.facade;

import lombok.Getter;
import ru.skitel.cloud.connection.PackageWriter;
import ru.skitel.cloud.connection.DatagramSender;

import java.io.IOException;

public abstract class ClientHelper<T>  {

    @Getter
    private static final PackageWriter channel = new DatagramSender();

    public abstract void getAndSendScreenshot() throws IOException;;

    public abstract void sendSnapshot(T snapshot) throws IOException;

}
