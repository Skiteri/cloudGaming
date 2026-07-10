package ru.skitel.cloud;

import ru.skitel.cloud.facade.ClientHelper;

public class ClientAppTest {

    public static void main(String[] args) {

        ClientHelper clientHelper = new ClientHelperTest();
        clientHelper.getAndSendScreenshot();

    }

}
