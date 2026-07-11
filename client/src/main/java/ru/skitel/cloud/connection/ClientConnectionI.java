package ru.skitel.cloud.connection;

import ru.skitel.cloud.ClientConnectionSingleton;
import ru.skitel.cloud.Data;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface ClientConnectionI {

    InetSocketAddress socketAddress = ClientConnectionSingleton.getInstance();

    void write(Data data);
    void init() throws IOException;


    static ClientConnectionI connect(ClientConnectionI connectionI) {
        try {
            connectionI.init();
            return connectionI;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
