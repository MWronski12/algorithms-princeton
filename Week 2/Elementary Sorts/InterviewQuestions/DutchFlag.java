package InterviewQuestions;

/*
    Question 3
    Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
    swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
    color(i): color of pebble in bucket i.
    The performance requirements are as follows:
    At most N calls to color().
    At most N calls to swap().
    Constant extra space.
*/

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;


public class DutchFlag {
    private final Bucket[] buckets;
    private final int n;

    public DutchFlag(int n) {
        buckets = new Bucket[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            buckets[i] = new Bucket(Pebble.randomPebble());
        }
    }

    private static class Bucket {
        public Pebble pebble;

        public Bucket(Pebble p) {
            pebble = p;
        }
    }

    public enum Pebble {
        Red,
        White,
        Blue;

        private static final Random PRNG = new Random();

        public static Pebble randomPebble() {
            Pebble[] pebbles = values();
            return pebbles[PRNG.nextInt(pebbles.length)];
        }
    }

    private void swap(int i, int j) {
        assert i >= 0 && i < n;
        assert j >= 0 && j < n;

        Pebble swap = buckets[i].pebble;
        buckets[i].pebble = buckets[j].pebble;
        buckets[j].pebble = swap;
    }

    private Pebble color(int i) {
        return buckets[i].pebble;
    }

    public void sort() {
        int head = 0;
        int tail = n - 1;
        int runner = 0;

        while (runner < tail) {
            if (color(runner) == Pebble.Red) {
                swap(head++, runner);
            } else if (color(runner) == Pebble.Blue) {
                swap(runner, tail--);
            } else if (color(runner) == Pebble.White) {
                runner++;
            }
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            StdOut.print(buckets[i].pebble + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        DutchFlag d = new DutchFlag(10);
        d.sort();
        d.print();
    }
}
