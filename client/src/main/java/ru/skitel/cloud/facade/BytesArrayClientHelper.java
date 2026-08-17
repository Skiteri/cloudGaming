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
//        getChannel().transfer(snapshot);
    }

}
