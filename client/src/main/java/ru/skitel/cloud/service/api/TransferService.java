package ru.skitel.cloud.service.api;

import java.io.IOException;

public interface TransferService<T> {

    void transfer(T data) throws IOException, InterruptedException;

}
