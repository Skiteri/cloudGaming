package ru.skitel.cloud.facade;

import lombok.Getter;
import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.api.PackageWriter;

import java.io.IOException;

public abstract class ClientHelper<T>  {

    @Getter
    private static final PackageWriter channel = new DatagramPackageWriter();

    public abstract void getAndSendScreenshot();
    public abstract void sendSnapshot(T snapshot) throws IOException;

}
