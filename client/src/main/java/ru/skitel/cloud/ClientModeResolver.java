package ru.skitel.cloud;

import lombok.Getter;
import ru.skitel.cloud.facade.BufferedImageClientHelper;
import ru.skitel.cloud.facade.BytesArrayClientHelper;
import ru.skitel.cloud.facade.ClientHelper;

import java.lang.reflect.InvocationTargetException;

public enum ClientModeResolver {

    BUFFERED_IMAGE_HELPER(Mode.BUFFERED_IMAGE_MODE, BufferedImageClientHelper.class),
    BYTE_ARRAY_HELPER(Mode.BYTE_ARRAY_MODE, BytesArrayClientHelper.class);

    @Getter
    private static ClientHelper<?> clientHelper;

    ClientModeResolver(Mode mode, Class<? extends ClientHelper<?>> clazz) {
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
