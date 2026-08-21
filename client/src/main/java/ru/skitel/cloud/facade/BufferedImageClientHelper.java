package ru.skitel.cloud.facade;

import ru.skitel.cloud.utils.ImageResizer;
import ru.skitel.cloud.service.DatagramChunkedTransferService;
import ru.skitel.cloud.service.ScreenCaptureService;
import ru.skitel.cloud.service.api.TransferService;
import ru.skitel.cloud.service.BufferedImageScreenCaptureService;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static ru.skitel.cloud.converter.ImageConverter.convert;

public class BufferedImageClientHelper extends ClientHelper<BufferedImage> {

    private final ScreenCaptureService<BufferedImage> bufferedImageScreenCaptureService = new BufferedImageScreenCaptureService();
    private final TransferService<byte[]> datagramTransferService = new DatagramChunkedTransferService();
    private final ImageResizer imageResizer = new ImageResizer();

    @Override
    public void getAndSendScreenshot() {
        BufferedImage screenshot = bufferedImageScreenCaptureService.getScreenImage();  // 100 ms
//        BufferedImage resize = imageResizer.resize(screenshot); //33 ms
//        Drawer.setImage(resize); //3 ms
//        BenchmarkMethod.benchmarking(() -> sendSnapshot(screenshot));
    }

    @Override
    public void sendSnapshot(BufferedImage snapshot) throws IOException, InterruptedException {
        byte[] picture = convert(snapshot);
        datagramTransferService.transfer(picture);
    }
}
