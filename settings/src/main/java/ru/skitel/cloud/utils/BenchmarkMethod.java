package ru.skitel.cloud.utils;

import java.io.IOException;
import java.util.function.Supplier;

public class BenchmarkMethod {

    @FunctionalInterface
    public interface Action {
        void action() throws IOException, InterruptedException; // Название метода может быть любым
    }

    public static void benchmarking(Action action) throws IOException, InterruptedException {
        long l = System.currentTimeMillis();
        action.action();
        System.out.println("Cистема выполнила действие за " + (System.currentTimeMillis() - l));
    }

    public static <T> T benchmarking(Supplier<T> action) {
        long l = System.currentTimeMillis();
        T t = action.get();
        System.out.println("Cистема выполнила действие за " + (System.currentTimeMillis() - l));
        return t;
    }

}
