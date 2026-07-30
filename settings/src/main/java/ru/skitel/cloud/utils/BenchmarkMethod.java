package ru.skitel.cloud.utils;

import java.util.function.Supplier;

public class BenchmarkMethod {

    @FunctionalInterface
    public interface Action {
        void action(); // Название метода может быть любым
    }

    public static void benchmarking(Action action) {
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
