package ru.skitel.cloud;

import lombok.Getter;
import ru.skitel.cloud.facade.BufferedImageServerHelper;
import ru.skitel.cloud.facade.ByteArrayServerHelper;
import ru.skitel.cloud.facade.ServerHelper;

import java.lang.reflect.InvocationTargetException;

public enum ServerModeResolver {

    BUFFERED_IMAGE_HELPER(Mode.BUFFERED_IMAGE_MODE, BufferedImageServerHelper.class),
    BYTE_ARRAY_HELPER(Mode.BYTE_ARRAY_MODE, ByteArrayServerHelper.class);

    @Getter
    private static ServerHelper<?> serverHelper;

    ServerModeResolver(Mode mode, Class<? extends ServerHelper<?>> clazz) {
        try {
            if (GlobalSettings.SERVER_MODE.getSetting() == mode) {
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
