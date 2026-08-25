package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;
import ru.skitel.cloud.factory.ClientHelperFactory;
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
        int i = 0;
        while (true) {
            BenchmarkMethod.benchmarking(clientHelper::getAndSendScreenshot);
//            clientHelper.getAndSendScreenshot();
        }
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
