package Sorting;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi < lo)
            return;
        int n = partition(arr, lo, hi);
        sort(arr, lo, n - 1);
        sort(arr, n + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo + 1;
        int j = hi;

        while (true) {

            while (i < hi && less(arr[i], arr[lo])) {
                i++;
            }

            while (j > lo && !less(arr[j], arr[lo])) {
                j--;
            }

            if (i >= j) {
                exch(arr, lo, j);
                return j;
            }

            exch(arr, i, j);
        }
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        if (v.compareTo(w) < 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Integer[] arr = { -123, 123, 235, 4, 6, 5, 235, -6, 4, -6, 7 - 23, 5, 3, -64, 23, 4, 6, 1, 6, -9, 0, 0, 0 };
        Quick.sort(arr);
        StdOut.print(Arrays.toString(arr));
    }
}
