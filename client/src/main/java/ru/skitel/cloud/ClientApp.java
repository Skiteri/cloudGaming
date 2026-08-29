package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.factory.ClientHelperFactory;
import ru.skitel.cloud.service.DatagramChunkedTransferService;
import ru.skitel.cloud.utils.BenchmarkMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ClientApp implements Runnable {

    private static void loadFromResources() {
        String dllName = "capture.dll";

        Path path = Paths.get( "native", dllName);
        Path absolutePath = path.toAbsolutePath();

        if (!Files.exists(absolutePath)) {
            throw new RuntimeException("Нативная библиотека не найдена по пути: " + absolutePath);
        }

        System.load(absolutePath.toString());
    }

    public static void main(String[] args) throws Exception {
        loadFromResources();
        start();
    }


    public static void start() throws IOException, InterruptedException {
        ClientHelper<?> clientHelper = ClientHelperFactory.getClientHelper();
        long l = System.currentTimeMillis();
        int i = 0;
        while (true) {
//        while (i < 120) {
            BenchmarkMethod.benchmarking(clientHelper::getAndSendScreenshot);
//            clientHelper.getAndSendScreenshot();
            i++;
        }
//        System.out.println("Отправлено " + i + " писисок за " + (System.currentTimeMillis() - l) + " милиписисек" );
//        System.out.println("Получено " + DatagramChunkedTransferService.getPacketSend() + " писисок за " + (System.currentTimeMillis() - l) + " милиписисек" );
//        System.out.println("не Отправлено " + DatagramChunkedTransferService.getPacketDrop() + " писисок за " + (System.currentTimeMillis() - l) + " милиписисек" );


    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
