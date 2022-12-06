package RandomizedQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    private class Node {
        Node prev;
        Node next;
        Item item;
    }

    // construct an empty deque
    public Dequeue() {
        first = null;
        last = null;
        n = 0;
    }

    public void print() {
        StdOut.print("[");
        int i = 1;
        for (Item item : this) {
            StdOut.print(item);
            if (i != n) {
                StdOut.print(", ");
            }
            i++;
        }
        StdOut.println("]");
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.prev = null;
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
            newFirst.next = null;
        } else {
            first.prev = newFirst;
            newFirst.next = first;
            first = newFirst;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        if (isEmpty()) {
            first = newLast;
            last = newLast;
            newLast.prev = null;
        } else {
            last.next = newLast;
            newLast.prev = last;
            last = newLast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) {
            first = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    private class DequeueIterator implements Iterator<Item> {

        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Dequeue<String> deque = new Dequeue<String>();
        deque.addFirst("first");
        deque.addLast("last");
        deque.addLast("last");
        deque.addFirst("first");
        deque.print();
        StdOut.print(deque.removeFirst() + " ");
        deque.print();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        deque.print();
    }
}
