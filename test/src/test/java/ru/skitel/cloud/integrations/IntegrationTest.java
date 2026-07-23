package ru.skitel.cloud.integrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.facade.BufferedImageClientHelper;
import ru.skitel.cloud.settings.Mode;
import ru.skitel.cloud.settings.PacketSettings;
import ru.skitel.cloud.settings.Resolution;
import ru.skitel.cloud.setting.ServerModeResolver;
import ru.skitel.cloud.converter.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.*;

import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class IntegrationTest {

    @Test
    public void testDataLengthLessThanPacketLength() throws ExecutionException, InterruptedException, IOException {
        GlobalSettings.setRESOLUTION(Resolution.RESOLUTION_4k);
        GlobalSettings.setPACKET_SETTINGS(new PacketSettings(65507, Resolution.RESOLUTION_4k.getPixelsCount()));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<byte[]> task = () -> (byte[]) new BufferedImageServerHelperTest().receiveScreen();
        Future<byte[]> future = executorService.submit(task);

        BufferedImage expected = create3by3();
        byte[] convert = ImageConverter.convert(expected);

        BufferedImageClientHelper a = new BufferedImageClientHelper();
        a.getAndSendScreenshot();

        byte[] gotImage = future.get();

        Assertions.assertArrayEquals(convert, gotImage);
    }

    @Test
    public void testDataLengthMoreThanPacketLength() throws ExecutionException, InterruptedException, IOException {
        GlobalSettings.setPACKET_SETTINGS(new PacketSettings(65507, 8400));
        GlobalSettings.setRESOLUTION(Resolution.RESOLUTION_ANY);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<byte[]> task = () -> (byte[]) new BufferedImageServerHelperTest().receiveScreen();
        Future<byte[]> future = executorService.submit(task);

        BufferedImage expected = create3by3();
        byte[] convert = ImageConverter.convert(expected);

        IntegrationHelper a = new IntegrationHelper();
        a.getAndSendScreenshot();

        byte[] gotImage = future.get();

        Assertions.assertArrayEquals(convert, gotImage);
    }
}
