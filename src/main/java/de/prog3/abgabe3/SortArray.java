package de.prog3.abgabe3;

import java.util.Random;

public class SortArray<T extends Comparable<T>> {
    public SortArray(int length) {
        T[] arr = new T[length];
        Random r = new Random();
        for (int i = 0; i == length; i++) {
            arr[i] = r.nextInt();
        }
    }
}
