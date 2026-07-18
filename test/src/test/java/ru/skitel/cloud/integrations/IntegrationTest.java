package ru.skitel.cloud.integrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.Mode;
import ru.skitel.cloud.setting.ServerModeResolver;
import ru.skitel.cloud.converter.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class IntegrationTest {

    @Test
    public void checkIntegration() throws ExecutionException, InterruptedException {
        GlobalSettings.setSERVER_MODE(Mode.BYTE_ARRAY_MODE);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<byte[]> task = () -> (byte[]) ServerModeResolver.getServerHelper().receiveScreen();
        Future<byte[]> future = executorService.submit(task);

        BufferedImage expected = create3by3();
        byte[] convert = ImageConverter.convert(expected);

        IntegrationHelper a = new IntegrationHelper();
        a.getAndSendScreenshot();

        byte[] gotImage = future.get();

        Assertions.assertArrayEquals(convert, gotImage);
    }

    private void initColors(Color[] colors) {
        colors[0] = Color.red;
        colors[1] = Color.blue;
        colors[2] = Color.black;
        colors[3] = Color.yellow;
        colors[4] = Color.gray;
        colors[5] = Color.green;
        colors[6] = Color.white;
        colors[7] = Color.magenta;
        colors[8] = Color.orange;
    }

}
