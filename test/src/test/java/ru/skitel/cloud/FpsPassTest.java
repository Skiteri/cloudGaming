package ru.skitel.cloud;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
