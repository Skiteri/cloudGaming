package ru.skitel.cloud;

import lombok.Getter;
import ru.skitel.cloud.api.Receiver;
import ru.skitel.cloud.service.datagram.DatagramServerReceiver;

@Getter
public enum ReceiverHolder {
    INSTANCE;

    private final Receiver<byte[]> receiver = ReceiverFactory.create();

    @SuppressWarnings(value = "unchecked")
    public <T> Receiver<T> getInstance() {
        return (Receiver<T>) receiver;
    }

    public static class ReceiverFactory {
        private static <T> Receiver<T> create() {
            return switch (GlobalSettings.getTypeMode()) {
                case DATAGRAM_PACKET -> new DatagramServerReceiver<>();
                default -> throw new RuntimeException();
            };
        }
    }
}
