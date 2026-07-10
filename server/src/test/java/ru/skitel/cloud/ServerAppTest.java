package ru.skitel.cloud;


import ru.skitel.cloud.facade.BufferedImageServerHelper;

public class ServerAppTest {

    public static void main(String[] args) {
        BufferedImageServerHelper bufferedImageServerHelper = new BufferedImageServerHelper();
        bufferedImageServerHelper.receiveAndDraw();
    }

}