package ru.skitel.cloud.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skitel.cloud.GlobalSettings;
import ru.skitel.cloud.Mode;
import ru.skitel.cloud.Resolution;
import ru.skitel.cloud.ServerTestMode;
import ru.skitel.cloud.connection.DatagramSender;
import ru.skitel.cloud.converter.ImageConverter;
import ru.skitel.cloud.integrations.IntegrationHelper;
import ru.skitel.cloud.service.datagram.DatagramServerReceiver;
import ru.skitel.cloud.setting.ServerModeResolver;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.*;

import static ru.skitel.cloud.converter.ImageConverter.convert;
import static ru.skitel.cloud.utils.ImageUtil.create3by3;

public class DatagramSenderClientTest {

    private final DatagramSender clientHelper = new DatagramSender();
    private final DatagramServerReceiver datagramServerReceiver = DatagramServerReceiver.DatagramSocketFactory.getInstance() ;

    @Test
    public void write() throws IOException {
        byte[] expected = new byte[10];
        initExpected(expected);
        byte[] result = new byte[3];

        clientHelper.write(result);

        byte[] pack = datagramServerReceiver.getPack();

        Assertions.assertArrayEquals(expected, pack);

    }

    @Test
    public void checkIntegration() throws ExecutionException, InterruptedException, IOException {
        GlobalSettings.setRESOLUTION(Resolution.RESOLUTION_ANY);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<byte[]> task = () -> (byte[]) ServerTestMode.getServerHelper().receiveScreen();
        Future<byte[]> future = executorService.submit(task);

        BufferedImage expected = create3by3();
        byte[] convert = convert(expected);

        IntegrationHelper a = new IntegrationHelper();
        a.getAndSendScreenshot();

        byte[] gotImage = future.get();

        Assertions.assertArrayEquals(convert, gotImage);
    }

    private void initExpected(byte[] expected) {

    }

}
