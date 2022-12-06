package RandomizedQueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private final int head;
    private int tail;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        q[tail] = item;
        tail++;
        n++;

        if (n == q.length) {
            resize(q.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniformInt(head, tail);
        Item item = q[i];
        tail--;
        n--;
        q[i] = q[tail];
        q[tail] = null;
        if (n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return q[StdRandom.uniformInt(head, tail)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ResizingArrayIterator();
    }

    public void print() {
        if (n == 0) {
            StdOut.print("[]\r\n");
            return;
        }
        for (int i = 0; i < q.length; i++) {
            if (i == 0) {
                StdOut.print("[" + q[head + i] + ", ");
            } else if (i == q.length - 1) {
                StdOut.print(q[head + i] + "]\r\n");
            } else {
                StdOut.print(q[head + i] + ", ");
            }
        }
    }

    private void resize(int capacity) {
        if (capacity < n) {
            throw new IllegalArgumentException();
        }

        q = Arrays.copyOf(q, capacity);
    }

    private class ResizingArrayIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i != n;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = q[(head + i) % q.length];
            i++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) {
                StdOut.print(q.dequeue() + " ");
            } else {
                q.enqueue(item);
            }
            q.print();
        }
    }
}
