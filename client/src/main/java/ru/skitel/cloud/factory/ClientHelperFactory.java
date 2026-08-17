package ru.skitel.cloud.factory;

import lombok.Getter;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.facade.*;
import ru.skitel.cloud.settings.Mode;

import java.lang.reflect.InvocationTargetException;

public enum ClientHelperFactory {

    BUFFERED_IMAGE_HELPER(Mode.BUFFERED_IMAGE_MODE, BufferedImageClientHelper.class),
    BYTE_ARRAY_HELPER(Mode.BYTE_ARRAY_MODE, BytesArrayClientHelper.class),
    MULTI_RESOLUTION_SCREEN_HELPER(Mode.MULTI_RESOLUTION_SCREEN_MODE, MultiResolutionScreenClientHelper.class),
    INT_ARRAY_SCREEN_HELPER(Mode.INT_ARRAY_SCREEN_MODE, IntArrayClientHelper.class);

    @Getter
    private static ClientHelper<?> clientHelper;

    ClientHelperFactory(Mode mode, Class<? extends ClientHelper<?>> clazz) {
        try {
            if (GlobalSettings.getClientMode() == mode) {
                setClientHelper(clazz.getDeclaredConstructor().newInstance());
            }
        } catch (RuntimeException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setClientHelper(ClientHelper<?> type) {
        clientHelper = type;
    }

}
