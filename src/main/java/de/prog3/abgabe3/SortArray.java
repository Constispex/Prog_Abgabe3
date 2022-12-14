package de.prog3.abgabe3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveTask;

public class SortArray<T extends Comparable<T>> extends RecursiveTask {
    final int SWITCH_SIZE = 10;
    private final int start;
    private final int end;
    private final T[] arr;
    private final T[] leftArray;
    private final T[] rightArray;
    private final Comparator<T> comp;

    SortArray(T[] arr, int start, int end, Comparator<T> c) {
        this.start = start;
        this.end = end;
        this.arr = arr;
        leftArray = Arrays.copyOfRange(arr, start, arr.length / 2);
        rightArray = Arrays.copyOfRange(arr, (arr.length / 2) + 1, end);
        comp = c;
    }

    private <T> void merge(T[] arr, int start, int mid, int end, Comparator<T> comp) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - start + 1;
        int n2 = end - mid;

        /* Merge temp arrays */
        int i = 0, j = 0;

        int k = start;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) != 1) {
                arr[k] = (T) leftArray[i];
                i++;
            } else {
                arr[k] = (T) rightArray[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = (T) leftArray[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = (T) rightArray[j];
            j++;
            k++;
        }
    }

    private void insertionSort(T[] array){
        for (int i = 1; i < array.length; i++)
        {
            int currentIndex = i;

            // If currentIndex min 1 and the item before currenIndex is greater than currentIndex -> Swap
            while (currentIndex > 0 && array[currentIndex - 1].compareTo(array[currentIndex]) > 0)
            {
                T temp = array[currentIndex];
                array[currentIndex] = array[currentIndex - 1];
                array[currentIndex - 1] = temp;
                currentIndex--;
            }
        }
    }

    @Override
    protected Object compute() {
        if (start - end <= SWITCH_SIZE) {
            insertionSort(arr);
        }
        else {
            int mid = (start + end) / 2;
            SortArray<T> right = new SortArray<>(arr, start, mid, comp);
            SortArray<T> left = new SortArray<>(arr, mid + 1, end, comp);

            right.fork();
            left.compute();
            right.join();

            merge(arr, start, mid, end, comp);
        }
        return arr;
    }


    public int compare(Object a, Object b) {
        return Integer.compare((Integer) a, (Integer) b);
    }
}

