package de.prog3.abgabe3;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class SortArray <T extends Comparable<T>> extends RecursiveAction {
    private static final int CHUNKSIZE = 20;
    private final int[] u;
    private final int length;

    public SortArray(int[] u, int length) {
        this.length = length;
        this.u = u;
    }
    public void sortArr(T[] arr) {

    }

    @Override
    protected void compute() {

    }
}
