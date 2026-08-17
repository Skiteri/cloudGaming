package ru.skitel.cloud.integrations;

import ru.skitel.cloud.connection.SocketConnection;
import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.service.DatagramChunkedTransferService;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.utils.ImageUtil;

import java.awt.image.BufferedImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class IntegrationHelper extends ClientHelper<BufferedImage> {

    private static final TransferService<byte[]> channel = new DatagramChunkedTransferService();

    @Override
    public void getAndSendScreenshot()  {
        BufferedImage bufferedImage = ImageUtil.create3by3();
        sendSnapshot(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) {
        byte[] picture = convert(snapshot);
        channel.transfer(picture);
    }

}