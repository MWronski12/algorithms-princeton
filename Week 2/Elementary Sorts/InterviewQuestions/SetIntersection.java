package InterviewQuestions;

/*
Question 1
Intersection of two sets.
Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
design a sub quadratic algorithm to count the number of points that are contained both in array a[] and array b[].
 */

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

public class SetIntersection {

    public static int count(Comparable[] a, Comparable[] b) {
        assert a.length == b.length;
        int n = a.length;

        Shell.sort(a);
        Shell.sort(b);

        int i = 0;
        int j = 0;
        int intersectionCount = 0;

        while (i < n && j < n) {
            int compare = a[i].compareTo(b[j]);
            if (compare < 0) {
                i++;
            } else if (compare > 0) {
                j++;
            } else {
                i++;
                intersectionCount++;
            }
        }

        return intersectionCount;
    }

    public boolean isPermutation(Comparable[] a, Comparable[] b) {
        assert a.length == b.length;
        int n = a.length;

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Point2D[] a = {new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3)};
        Point2D[] b = {new Point2D(1, 2), new Point2D(3, 3), new Point2D(1, 3)};

        Integer[] c = {1, 2, 3, 4, 5, 6};
        Integer[] d = {5, 6, 7, 8, 9, 10};

        StdOut.println(SetIntersection.count(a, b));
        StdOut.println(SetIntersection.count(c, d));
    }
}
