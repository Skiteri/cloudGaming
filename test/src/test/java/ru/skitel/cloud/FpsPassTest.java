package ru.skitel.cloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.DatagramPacket;

@ExtendWith(MockitoExtension.class)
public class FpsPassTest {

    @Mock
    private ClientApp clientApp;

    @BeforeAll
    public static void init() {
//        GlobalSettings.setResolution(Resolution.RESOLUTION_4k);
    }

    @Test
    public void passTime() throws InterruptedException {
        Thread thread = new Thread(new ServerApp());
        thread.start();
//        benc();
    }


    @Test
    public void passsTime() throws InterruptedException {
//        int length = GlobalSettings.getResolution().getPixelsCount() * 3;
//        int maxPacketLength = 65507;
//        int countSending = (int) Math.ceil((double) length / 65507);
//        int lastPacketCount = maxPacketLength * countSending - length;
//        for (int i = 0; i < countSending; i++) {
//            DatagramPacket datagramPacket = new DatagramPacket(bytes, i, maxPacketLength);
//            datagramSocket.send(datagramPacket);
//        }
//
//        DatagramPacket datagramPacket = new DatagramPacket(bytes, countSending, lastPacketCount);
//        datagramSocket.send(datagramPacket);

        int length = 21;
        int maxPacketLength = 4;
        int countSending = (int) Math.ceil((double) length / maxPacketLength);
        System.out.println(countSending);


        for (int i = 0; i < countSending - 1; i++) {
            System.out.println(i  + ": " + (i + 1) * maxPacketLength);
        }
//        }

        int lastPacketCount = length - maxPacketLength * (countSending - 1);

        System.out.println(countSending + ": " + (maxPacketLength * (countSending - 1) + lastPacketCount));


    }

//    private static void benc() throws InterruptedException {
//        DatagramConnection channel = (DatagramConnection) ClientConnectionI.connect(new DatagramConnection());
//        long aw = System.currentTimeMillis();
//        for (int frame = 0; frame < 1; frame++) {
//            byte[][] picture = new Picture().getPixelsByte();
//            for (int i = 0; i < picture.length; i++) {
//
//                channel.write(picture[i]);
//                if (i % 140 == 0) {
//                    System.out.println(i % Setting.getResolution().getHeight());
//                    Thread.sleep(50);
//                }
//            }
//        }
//        System.out.println(System.currentTimeMillis() - aw);
//    }
}
