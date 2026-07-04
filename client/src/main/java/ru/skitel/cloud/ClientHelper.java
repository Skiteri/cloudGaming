package ru.skitel.cloud;

import lombok.Getter;

public abstract class ClientHelper<T> {

    @Getter
    private static final DatagramConnection channel = (DatagramConnection) ClientConnectionI.connect(new DatagramConnection());

    public abstract void getAndSendScreenshot();

    public abstract void sendSnapshot(T snapshot);

}
