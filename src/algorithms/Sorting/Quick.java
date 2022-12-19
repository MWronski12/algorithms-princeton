package Sorting;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * Time complexity: O(N^2) - theoretical worst case scenario
 * Space complexity: O(1)
 *
 * Though the running time in worst case scenario is O(N^2),
 * which happens when the array is already sorted,
 * actual running time on average is ~ 2N * ln(N) ~ 1.39N * lg(N).
 */

public class Quick {

    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo)
            return;
        int n = partition(arr, lo, hi);
        sort(arr, lo, n - 1);
        sort(arr, n + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {

            while (less(arr[++i], arr[lo])) {
                if (i == hi)
                    break;
            }

            while (less(arr[lo], arr[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(arr, i, j);
        }

        exch(arr, lo, j);
        return j;
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
