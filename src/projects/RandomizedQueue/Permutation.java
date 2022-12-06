package RandomizedQueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public Permutation() {
    }

    static void readStrings(RandomizedQueue<String> q) {
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
    }

    public static void main(String[] args) {

        RandomizedQueue<String> q = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        StdOut.println(k);

        readStrings(q);
        while (k > 0 && !q.isEmpty()) {
            StdOut.println(q.dequeue());
            k--;
        }
    }
}
