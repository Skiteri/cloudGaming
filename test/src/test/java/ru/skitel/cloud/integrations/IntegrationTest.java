package ru.skitel.cloud.integrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.client.ClientHelperTest;
import ru.skitel.cloud.facade.ByteArrayServerHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

import static ru.skitel.cloud.converter.ImageConverter.convert;
import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class IntegrationTest {

    @Test
    public void testDataLengthLessThanPacketLength() {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Callable<byte[]> task = () -> (byte[]) new ByteArrayServerHelper().receiveScreen();
            Future<byte[]> future = executorService.submit(task);

            BufferedImage expected = create3by3();

            ClientHelperTest a = new ClientHelperTest();
            a.getAndSendScreenshot();
            byte[] convert = convert(expected);
            byte[] gotImage = future.get();
            executorService.shutdownNow();
            Assertions.assertArrayEquals(convert, gotImage);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

//    @Test
//    public void testDataLengthMoreThanPacketLength() throws ExecutionException, InterruptedException, IOException {
//        GlobalSettings.setPACKET_SETTINGS(new PacketSettings(65507));
//        GlobalSettings.setRESOLUTION(Resolution.RESOLUTION_ANY);
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Callable<byte[]> task = () -> (byte[]) new BufferedImageServerHelperTest().receiveScreen();
//        Future<byte[]> future = executorService.submit(task);
//
//        BufferedImage expected = create3by3();
//        byte[] convert = ImageConverter.convert(expected);
//
//        IntegrationHelper a = new IntegrationHelper();
//        a.getAndSendScreenshot();
//
//        byte[] gotImage = future.get();
//
//        Assertions.assertArrayEquals(convert, gotImage);
//    }
}
