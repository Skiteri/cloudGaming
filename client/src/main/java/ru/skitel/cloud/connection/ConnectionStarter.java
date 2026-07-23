package ru.skitel.cloud.connection;

public abstract class ConnectionStarter {

    public ConnectionStarter() {
    }

    public final void start() {
        openConnection();
    }

    public abstract void openConnection();

}
