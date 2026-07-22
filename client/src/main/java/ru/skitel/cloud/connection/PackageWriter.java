package ru.skitel.cloud.connection;

import java.io.IOException;

public interface PackageWriter<T> {

    void write(T data) throws IOException;

}
