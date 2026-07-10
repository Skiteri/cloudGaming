package ru.skitel.cloud.facade;

import lombok.Getter;
import ru.skitel.cloud.connection.ClientConnectionI;
import ru.skitel.cloud.connection.DatagramConnection;

public abstract class ClientHelper<T>  {

    @Getter
    private static final DatagramConnection channel = (DatagramConnection) ClientConnectionI.connect(new DatagramConnection());

    public abstract void getAndSendScreenshot();

    public abstract void sendSnapshot(T snapshot);

}
