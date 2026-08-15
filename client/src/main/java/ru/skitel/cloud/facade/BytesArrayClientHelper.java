package ru.skitel.cloud.facade;

import ru.skitel.cloud.service.ByteArrayScreenCaptureService;


public class BytesArrayClientHelper extends ClientHelper<byte[]> {

    ByteArrayScreenCaptureService byteArrayScreenCaptureService = new ByteArrayScreenCaptureService();

    @Override
    public void getAndSendScreenshot() {
        while (true) {
            byte[] screenshot = byteArrayScreenCaptureService.getScreenImage();
            sendSnapshot(screenshot);
        }
    }

    @Override
    public void sendSnapshot(byte[] snapshot) {
        getChannel().write(snapshot);
    }


//    private static void sendSnapshot(Object snapshot) {
//        for (int frame = 0; frame < 1; frame++) {
//            byte[][] picture = new Picture().getPixelsByte();
//            for (int i = 0; i < picture.length; i++) {
//                channel.write(picture[i]);
//                System.out.println(i);
//                timeout(i);
//            }
//        }
//    }

}
