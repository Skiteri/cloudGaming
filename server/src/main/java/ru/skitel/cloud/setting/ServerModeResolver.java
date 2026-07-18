package ru.skitel.cloud.setting;

import lombok.Getter;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.Mode;
import ru.skitel.cloud.facade.BufferedImageServerHelper;
import ru.skitel.cloud.facade.ByteArrayServerHelper;
import ru.skitel.cloud.api.ServerHelper;

import java.lang.reflect.InvocationTargetException;

public enum ServerModeResolver {

    BUFFERED_IMAGE_HELPER(Mode.BUFFERED_IMAGE_MODE, BufferedImageServerHelper.class),
    BYTE_ARRAY_HELPER(Mode.BYTE_ARRAY_MODE, ByteArrayServerHelper.class);

    @Getter
    private static ServerHelper<?> serverHelper;

    ServerModeResolver(Mode mode, Class<? extends ServerHelper<?>> clazz) {
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
