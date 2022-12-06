package Queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<Item> implements Iterable<Item> {

    private Node head = null;
    private Node tail = null;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node temp = head;
        head = temp.next;
        return temp.item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void print() {
        Node temp = head;
        StdOut.print("[");
        while (temp != null) {
            StdOut.print(temp.item + ", ");
            temp = temp.next;
        }
        StdOut.print("\b\b]\n");
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node current = head;

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
            Node temp = current;
            current = temp.next;
            return temp.item;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<>();

        while (true) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
            for (String s : queue) {
                StdOut.print(s + " ");
            }
        }
    }
}
