package ru.skitel.cloud.setting;


import lombok.Getter;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.api.ServerHelper;
import ru.skitel.cloud.facade.BufferedImageServerHelper;
import ru.skitel.cloud.facade.ByteArrayServerHelper;
import ru.skitel.cloud.settings.Mode;

import java.awt.image.BufferedImage;

public enum ServerModeSingleton {

    INSTANCE;

    @Getter
    private final ServerHelper<?> serverHelper = ServerHelperFactory.getServerHelper();

    public enum ServerHelperFactory {

        BUFFERED_IMAGE_MODE(Mode.BUFFERED_IMAGE_MODE) {
            @Override
            public ServerHelper<BufferedImage> createHelper() {
                return new BufferedImageServerHelper();
            }
        },
        BYTE_ARRAY_MODE(Mode.BYTE_ARRAY_MODE) {
            @Override
            public ServerHelper<byte[]> createHelper() {
                return new ByteArrayServerHelper();
            }
        };

        ServerHelperFactory(Mode mode) {
            if (mode == GlobalSettings.getServerMode()) {
                init(this);
            }
        }

        @Getter
        private static ServerHelper serverHelper;

        private static void init(ServerHelperFactory serverFactory) {
            serverHelper = serverFactory.createHelper();
        }

        public abstract ServerHelper createHelper();


    }

}
