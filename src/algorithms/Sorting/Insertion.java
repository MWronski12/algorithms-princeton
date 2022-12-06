package Sorting;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/*
 * Number of compare operations: ~ (N^2)/4 (Best case N - 1)
 * Number of exchange operations: ~ (N^2)/4 (Best case 0)
 * Time complexity: O(N^2)
 * Space complexity: O(1)
 *
 * Inversion is a pair of keys that is out of order.
 * Array is partially sorted if number of inversions < c*N.
 * Insertion sort runs in linear time if an array is partially sorted.
 *      Proof: Number of exchanges equals the number of inversions.
 */

public class Insertion {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {

            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] arr, int v, int w) {
        Object swap = arr[v];
        arr[v] = arr[w];
        arr[w] = swap;
    }

    public static void main(String[] args) {
        String[] arr = { "buszek", "smierdziuszek", "arab", "pekly", "trzy", "dyszki" };
        Insertion.sort(arr);
        StdOut.print(Arrays.toString(arr));
    }
}
