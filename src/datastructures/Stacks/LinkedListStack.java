package Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<Item> implements Iterable<Item> {

    private Node first;

    public LinkedListStack() {
        first = null;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
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

    private class Node {
        Item item;
        Node next;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node temp = first;
        first = first.next;
        return temp.item;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int count() {
        if (isEmpty()) {
            return 0;
        }
        int n = 1;
        for (Node temp = first; temp.next != null; temp = temp.next) {
            n++;
        }
        return n;
    }

    public void print() {
        for (Item item : this) {
            StdOut.print(item + " ");
        }
    }

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.push("siema");
        stack.push("mordo");
        stack.print();
        StdOut.println(stack.count());
        StdOut.println(stack.pop());
        StdOut.println(stack.count());
    }
}
