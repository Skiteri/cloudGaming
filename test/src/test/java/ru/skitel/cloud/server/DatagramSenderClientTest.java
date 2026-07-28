package ru.skitel.cloud.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.integrations.BufferedImageServerHelperTest;
import ru.skitel.cloud.integrations.IntegrationHelper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.*;

import static ru.skitel.cloud.converter.ImageConverter.convert;
import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class DatagramSenderClientTest {

    @Test
    public void checkIntegration() throws ExecutionException, InterruptedException, IOException {
        Callable<byte[]> task = () -> {
            BufferedImageServerHelperTest bufferedImageServerHelperTest = new BufferedImageServerHelperTest();
            return (byte[]) bufferedImageServerHelperTest.receiveScreen();
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<byte[]> future = executorService.submit(task);

        IntegrationHelper a = new IntegrationHelper();
        a.getAndSendScreenshot();

        BufferedImage expected = create3by3();
        byte[] convert = convert(expected);
        byte[] gotImage = future.get();
        executorService.shutdownNow();
        Assertions.assertArrayEquals(convert, gotImage);
    }


}
