package ru.skitel.cloud;

public abstract class ClientConnector {

    public ClientConnector() {
        openConnection();
    }

    public abstract void openConnection();

}
