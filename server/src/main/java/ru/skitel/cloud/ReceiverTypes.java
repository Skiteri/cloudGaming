package ru.skitel.cloud;

import ru.skitel.cloud.api.Receiver;

public interface ReceiverTypes {
    <T> Receiver<T> getReceiver();
}
