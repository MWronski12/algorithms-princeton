package Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {

    private Item[] s;
    private int N;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        s = (Item[]) new Object[capacity];
    }

    public Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public void push(Item item) {
        if (N == s.length) {
            throw new StackOverflowError();
        }
        s[N++] = item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int count() {
        return N;
    }

    public void print() {
        for (int i = 0; i < N; i++) {
            StdOut.println(s[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push("siema");
        StdOut.println(stack.count());
        stack.print();
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return s[i] != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = s[i];
            i++;
            return item;
        }
    }
}
