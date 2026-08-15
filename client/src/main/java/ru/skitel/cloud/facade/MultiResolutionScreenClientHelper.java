package ru.skitel.cloud.facade;

import ru.skitel.cloud.service.DatagramPackageWriter;
import ru.skitel.cloud.service.MultiResolutionScreenCaptureService;
import ru.skitel.cloud.service.api.PackageWriter;

import java.awt.image.MultiResolutionImage;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class MultiResolutionScreenClientHelper extends ClientHelper<MultiResolutionImage>{

    private final MultiResolutionScreenCaptureService bufferedImageScreenCaptureService = new MultiResolutionScreenCaptureService();
    private final PackageWriter datagramPackageWriter = new DatagramPackageWriter();

    @Override
    public void getAndSendScreenshot() {
        MultiResolutionImage screenshot = bufferedImageScreenCaptureService.getScreenImage();
        sendSnapshot(screenshot);
    }

    @Override
    public void sendSnapshot(MultiResolutionImage snapshot) {
//        byte[] picture = convert(snapshot);
//        try {
//            datagramPackageWriter.write(picture);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
