package ru.skitel.cloud.service.api;

import java.io.IOException;

public interface PackageWriter<T> {

    void write(T data) throws IOException;

}
