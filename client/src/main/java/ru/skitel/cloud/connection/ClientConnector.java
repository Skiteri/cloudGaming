package ru.skitel.cloud.connection;

public abstract class ClientConnector {

    public ClientConnector() {
        openConnection();
    }

    public abstract void openConnection();

}
