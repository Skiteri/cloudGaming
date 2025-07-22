package ru.skitel.cloud;

import java.io.IOException;
import java.net.InetSocketAddress;

import static ru.skitel.cloud.Resolution.FPS;

public interface ClientConnectionI {

    InetSocketAddress inetSocketAddress = new InetSocketAddress(ConnectionUtil.HOST, ConnectionUtil.PORT);
    InetSocketAddress localSocketAddress = new InetSocketAddress(ConnectionUtil.LOCALHOST, ConnectionUtil.PORT);
    long TIMEOUT = 1000 / FPS;

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
