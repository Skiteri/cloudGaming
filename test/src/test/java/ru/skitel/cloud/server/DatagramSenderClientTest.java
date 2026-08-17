package ru.skitel.cloud.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.facade.ByteArrayServerHelper;
import ru.skitel.cloud.integrations.IntegrationHelper;

import java.awt.image.BufferedImage;
import java.util.concurrent.*;

import static ru.skitel.cloud.converter.ImageConverter.convert;
import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class DatagramSenderClientTest {

    @Test
    public void checkIntegration()  {
        Callable<byte[]> task = () -> {
            ByteArrayServerHelper bufferedImageServerHelperTest = new ByteArrayServerHelper();
            return bufferedImageServerHelperTest.receiveScreen();
        };
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<byte[]> future = executorService.submit(task);

            IntegrationHelper a = new IntegrationHelper();
            a.getAndSendScreenshot();

            BufferedImage expected = create3by3();
            byte[] convert = convert(expected);
            byte[] gotImage = future.get();
            executorService.shutdownNow();
            Assertions.assertArrayEquals(convert, gotImage);
        } catch (ExecutionException | InterruptedException e) {
            Assertions.fail(e.getCause() + " " + e.getMessage());
        }
    }
}
