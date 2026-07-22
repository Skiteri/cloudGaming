package ru.skitel.cloud.integrations;

import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.utils.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class IntegrationHelper extends ClientHelper<BufferedImage> {

    @Override
    public void getAndSendScreenshot() throws IOException {
        BufferedImage bufferedImage = ImageUtil.create3by3();
        sendSnapshot(bufferedImage);
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) throws IOException {
        byte[] picture = convert(snapshot);
        getChannel().write(picture);
    }

}