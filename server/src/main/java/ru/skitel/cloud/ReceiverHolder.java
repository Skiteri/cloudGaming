package ru.skitel.cloud;

import lombok.Getter;
import ru.skitel.cloud.api.ReceiverService;
import ru.skitel.cloud.service.datagram.ByteArrayReceiverService;

@Getter
public enum ReceiverHolder {
    INSTANCE;

    private final ReceiverService<byte[]> receiverService = ReceiverFactory.create();

    @SuppressWarnings(value = "unchecked")
    public <T> ReceiverService<T> getInstance() {
        return (ReceiverService<T>) receiverService;
    }

    public static class ReceiverFactory {
        private static <T> ReceiverService<T> create() {
            return switch (GlobalSettings.getTypeMode()) {
                case DATAGRAM_PACKET -> new ByteArrayReceiverService<>();
                default -> throw new RuntimeException();
            };
        }
    }
}
