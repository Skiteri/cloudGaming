package ru.skitel.cloud;

import ru.skitel.cloud.service.ByteArrayScreenCaptureServiceImpl;


public class BytesArrayClientHelper extends ClientHelper<byte[]> {

    ByteArrayScreenCaptureServiceImpl byteArrayScreenCaptureService = new ByteArrayScreenCaptureServiceImpl();

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

    //todo
    public static void drawScreen(byte[] bufferImage) {
//        BufferedImageCanvas canvas = new BufferedImageCanvas();
//        canvas.setImg(bufferImage);
//        frame.setVisible(true);
//        frame.add(canvas);
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
