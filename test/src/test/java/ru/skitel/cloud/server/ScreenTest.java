package ru.skitel.cloud.server;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ScreenTest {
//
//    public DatagramServerReceiver datagramServerReceiver = Mockito.mock(DatagramServerReceiver.class);
//
//    private final ImageCollectorService collector = new BufferedImageCollectorServiceImpl(datagramServerReceiver);

//    @Test
//    public void passPackagesMoreThan4K() {
//        int maxPacketLength = 3;
//        int iterations = 4;
//        byte[] packet = new byte[maxPacketLength];
//        byte[] result = new byte[10];
//        byte[] expected = new byte[10];
//        initPacket(packet);
//        initExpectedPacket(expected);
//
//        Mockito.when(datagramServerReceiver.getPack()).thenReturn(packet);
//        Mockito.when(datagramServerReceiver.getPack()).thenReturn(packet);
//
//        collector.collect();
//
//        Assertions.assertArrayEquals(result, expected);
//    }

//    @Test
//    public void pass2PackagesMoreThan4K() {
//        int maxPacketLength = 4;
//        int iterations = 3;
//
//        byte[] packet = new byte[maxPacketLength];
//        init2Packet(packet);
//        byte[] result = new byte[10];
//        byte[] expected = new byte[10];
//        initExpected2Packet(expected);
//
//        Mockito.doReturn(packet).when(datagramServerReceiver).getPack();
//
//        collector.collect();
//
//        Assertions.assertArrayEquals(result, expected);
//    }

    private void initPacket(byte[] packet) {
        packet[0] = 0;
        packet[1] = 1;
        packet[2] = 2;
    }

    private void init2Packet(byte[] packet) {
        packet[0] = 0;
        packet[1] = 1;
        packet[2] = 2;
        packet[3] = 3;
    }

    private void initExpectedPacket(byte[] packet) {
        packet[0] = 0;
        packet[1] = 1;
        packet[2] = 2;
        packet[3] = 0;
        packet[4] = 1;
        packet[5] = 2;
        packet[6] = 0;
        packet[7] = 1;
        packet[8] = 2;
        packet[9] = 0;
    }

    private void initExpected2Packet(byte[] packet) {
        packet[0] = 0;
        packet[1] = 1;
        packet[2] = 2;
        packet[3] = 3;
        packet[4] = 0;
        packet[5] = 1;
        packet[6] = 2;
        packet[7] = 3;
        packet[8] = 0;
        packet[9] = 1;
    }
}
