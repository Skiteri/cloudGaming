//package ru.skitel.cloud.client;
//
//import ru.skitel.cloud.facade.ClientHelper;
//import ru.skitel.cloud.utils.ImageUtil;
//
//import java.awt.image.BufferedImage;
//
//import static ru.skitel.cloud.converter.ImageConverter.convert;
//
//public class ClientHelperTest extends ClientHelper<BufferedImage> {
//
//    @Override
//    public void getAndSendScreenshot() {
//        BufferedImage bufferedImage = ImageUtil.create3by3();
//        sendSnapshot(bufferedImage);
//    }
//
//    @Override
//    public void sendSnapshot(BufferedImage snapshot) {
//        byte[] picture = convert(snapshot);
//        getChannel().write(picture);
//    }
//
//}