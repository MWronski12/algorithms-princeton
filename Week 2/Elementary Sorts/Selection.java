import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
 * Number of compare operations: (N - 1) + (N - 2) + ... + 1 ~ (N^2)/2
 * Number of exchange operations: N
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 */
public class Selection {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {

            int min = i;
            for (int j = i + 1; j < n; j++) {

                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            exch(arr, i, min);
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
        String[] arr = {"buszek", "smierdziuszek", "arab", "pekly", "trzy", "dyszki"};
        Selection.sort(arr);
        StdOut.print(Arrays.toString(arr));
    }
}
