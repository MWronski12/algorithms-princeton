package Sorting;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BottomUpMerge {

    static Comparable[] aux;

    private static void merge(Comparable[] arr, int low, int mid, int high) {

        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++)
            aux[k] = arr[k];

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

        for (int sz = 1; sz < n; sz *= 2) {
            for (int lo = 0; lo < n - sz; lo += 2 * sz) {
                merge(arr, lo, lo + sz / 2 + 1, Math.min(lo + 2 * sz - 1, n - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 5, 22, 1, 12, 13 };
        Merge.sort(arr);
        StdOut.println(Arrays.toString(arr));
    }
}
