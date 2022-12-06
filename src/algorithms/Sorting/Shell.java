package Sorting;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Shell {

    public static void sort(Comparable[] arr) {
        // sequence: (3^k - 1) / 2  --> k = lg3(2*n +1) = ln(2*n + 1) / ln(3)
        // k for max x of sequence such that x < n: k = (int) log3(2 * n + 1)
        // int k_max = (int) (Math.log(2 * n + 1) / Math.log(3));
        // int h = (int) (Math.pow(3, k_max) - 1) / 2;

        int n = arr.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h > 0) {
            StdOut.println(h + "-sorting...");

            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    exch(arr, j, j - h);
                }
            }

            // Is equivalent to (int) (Math.pow(3, --k) - 1) / 2
            h = h / 3;
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
        String[] arr = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Shell.sort(arr);
        StdOut.print(Arrays.toString(arr));
    }
}
