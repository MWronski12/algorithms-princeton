package Sorting;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Merge {

    static Comparable[] aux;

    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int high) {

        int i = low;
        int j = mid + 1;

        // Return if arr is already sorted
        if (arr[j - 1].compareTo(arr[j]) <= 0) {
            return;
        }

        for (int k = low; k <= high; k++) aux[k] = arr[k];

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        aux = Arrays.copyOf(arr, n);
        sort(arr, aux, 0, n - 1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 22, 1, 12, 2, 33, 24, 6, 7, 3, 24, 35, 72, 1, 2};
        Merge.sort(arr);
        StdOut.println(Arrays.toString(arr));
    }
}
