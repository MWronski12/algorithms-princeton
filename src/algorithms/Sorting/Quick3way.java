package Sorting;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * This version of Quicksort is optimized in case of multiple duplicate keys,
 * improving best case scenario of all identical keys to O(N).
 */

public class Quick3way {

    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo)
            return;

        int lt = 0, i = lo + 1, gt = hi;
        while (i <= gt) {
            int cmp = arr[i].compareTo(arr[lo]);
            if (cmp < 0)
                exch(arr, lt++, i++);
            if (cmp > 0)
                exch(arr, i, gt--);
            else
                i++;
        }
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = { -123, 123, 235, 4, 6, 5, 235, -6, 4, -6, 7 - 23, 5, 3, -64, 23, 4, 6, 1, 6, -9, 0, 0, 0 };
        Quick.sort(arr);
        StdOut.print(Arrays.toString(arr));
    }
}
