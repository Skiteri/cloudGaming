package ru.skitel.cloud;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.ByteBuffer;

@Getter
@Setter
public class Data implements Serializable {

    private byte[] bytes;
    private ByteBuffer byteBuffer;

    public Data(byte[] bytes) {
        this.bytes = bytes;
    }
    public Data(ByteBuffer bytes) {
        byteBuffer = bytes;
    }

}
