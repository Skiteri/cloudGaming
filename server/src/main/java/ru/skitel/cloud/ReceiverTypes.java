package ru.skitel.cloud;

import ru.skitel.cloud.api.ReceiverService;

public interface ReceiverTypes {
    <T> ReceiverService<T> getReceiver();
}
