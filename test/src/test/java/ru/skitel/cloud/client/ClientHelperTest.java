package ru.skitel.cloud.client;

import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.service.DatagramChunkedTransferService;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.utils.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class ClientHelperTest extends ClientHelper<BufferedImage> {

    private static final TransferService<byte[]> channel = new DatagramChunkedTransferService();

    @Override
    public void getAndSendScreenshot() throws IOException, InterruptedException {
        BufferedImage bufferedImage = ImageUtil.create3by3();
        sendSnapshot(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) throws IOException, InterruptedException {
        byte[] picture = convert(snapshot);
        channel.transfer(picture);
    }

}