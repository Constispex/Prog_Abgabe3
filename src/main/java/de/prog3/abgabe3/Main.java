package de.prog3.abgabe3;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        final int ARR_SIZE = 30;
        final int nThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool exec = new ForkJoinPool(nThreads);

        Integer[] arr = createArray(ARR_SIZE);
        Integer[] copyOfArr = Arrays.copyOf(arr, arr.length);

        arr = (Integer[]) exec.invoke(new SortArray<Integer>(arr, 0, ARR_SIZE, Integer::compareTo));

        compareArrays(arr, copyOfArr);
    }

    private static void compareArrays(Integer[] arr, Integer[] copyOfArr) {
        boolean cmp = arr.length == copyOfArr.length;
        Arrays.parallelSort(copyOfArr);
        for (int i = 0; cmp && i<arr.length ; ++i) {
            cmp = arr[i]. compareTo ( copyOfArr [i]) == 0;
        }
        System .out. printf ("Felder stimmen %s überein!", cmp ? "" : "nicht ");
    }

    private static Integer[] createArray(int length) {
        Integer[] arr = new Integer[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(0, 100);
        }
        return arr;
    }
}