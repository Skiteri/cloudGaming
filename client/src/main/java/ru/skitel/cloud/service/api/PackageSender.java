package ru.skitel.cloud.service.api;


import java.io.IOException;

public interface PackageSender<T> {

    void send(T bytes) throws IOException;

}
