package de.prog3.abgabe3;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {
        final int ARR_SIZE = 10;
        final int nThreads = Runtime.getRuntime().availableProcessors();
        final int[] arr = createArray(ARR_SIZE);

        ForkJoinPool exec = new ForkJoinPool(nThreads);
        exec.invoke(new SortArray(arr, 10));
    }

    private static int[] createArray(int length) {
        int[] arr = new int[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(0, 100);
        }
        return arr;
    }
}