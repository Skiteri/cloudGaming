package ru.skitel.cloud.connection;

public interface ClientConnectionI<T> {

    void write(T data);

}
