package ru.skitel.cloud.connection;

import ru.skitel.cloud.ConnectionUtil;
import ru.skitel.cloud.Data;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface ClientConnectionI {

    InetSocketAddress inetSocketAddress = new InetSocketAddress(ConnectionUtil.HOST, ConnectionUtil.PORT);
    InetSocketAddress localSocketAddress = new InetSocketAddress(ConnectionUtil.LOCALHOST, ConnectionUtil.PORT);

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
