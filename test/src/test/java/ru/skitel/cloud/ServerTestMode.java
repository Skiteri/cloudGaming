package ru.skitel.cloud;

import lombok.Getter;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.integrations.BufferedImageServerHelperTest;

import java.lang.reflect.InvocationTargetException;

public enum ServerTestMode {

    TEST_MODE(Mode.BUFFERED_IMAGE_MODE, BufferedImageServerHelperTest.class);

    @Getter
    private static ServerHelper<?> serverHelper;

    ServerTestMode(Mode mode, Class<? extends ServerHelper<?>> clazz) {
        try {
            if (GlobalSettings.getServerMode() == mode) {
                setServerHelper(clazz.getDeclaredConstructor().newInstance());
            }
        } catch (RuntimeException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setServerHelper(ServerHelper<?> type) {
        serverHelper = type;
    }


}
