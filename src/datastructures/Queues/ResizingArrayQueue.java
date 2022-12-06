package Queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;
    private int head;
    private int tail;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        n = 0;
    }

    public void enqueue(Item item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[tail] = item;
        tail++;
        if (tail == q.length) {
            tail = 0;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = q[head];
        q[head] = null;
        head++;
        n--;
        if (head == q.length) {
            head = 0;
        }
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void print() {
        if (isEmpty()) {
            StdOut.print("[]");
        } else {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    StdOut.print("[");
                }
                StdOut.print(q[(head + i) % q.length]);
                if (i < n - 1) {
                    StdOut.print(", ");
                }
                if (i == n - 1) {
                    StdOut.print("]\n");
                }
            }
        }
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(head + i) % q.length];
        }
        head = 0;
        tail = n;
        q = temp;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item next = q[(head + i) % q.length];
            i++;
            return next;
        }
    }


    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();
        while (true) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
            queue.print();
        }
    }
}
