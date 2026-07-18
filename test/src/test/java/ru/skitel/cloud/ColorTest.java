package ru.skitel.cloud;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ColorTest {

    public static void main(String[] args) throws AWTException {
        ColorTest colorTest = new ColorTest();
        Robot robot = new Robot();
        int x = 100, y = 20;
        Color pixelColor = robot.getPixelColor(x, y);
        System.out.println(Arrays.toString(pixelColor.getColorComponents(null)));

//        Color recievedColor = Color.getRGBColorComponents();
        ByteBuffer color1 = colorTest.getColor(x, y);
        ByteBuffer color = color1;
        System.out.println(Arrays.toString(color.array()));
    }

    public ByteBuffer getColor(int x, int y) {

        return Picture.getColor(x, y);
    }
}
