package Stacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Objects;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] s;
    private int N;

    public ResizingArrayStack() {
        N = 0;
        s = (Item[])new Object[1];
    }

    public Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    public void push(Item item) {
        if (N == s.length) {
            resize(2 * s.length);
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

    private void resize(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        s = Arrays.copyOf(s, capacity);
    }

    public static void main(String[] args) {

        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        String cmd;
        String item = "";
        StdOut.println("API: { push <item>, pop, count, print, exit }\n");

        while (true) {
            try {
                StdOut.print("Pass the command: ");
                cmd = StdIn.readString();
                if (Objects.equals(cmd, "push")) {
                    item = StdIn.readString();
                }
                switch (cmd) {
                    case "push":
                        stack.push(item);
                        break;
                    case "pop":
                        stack.pop();
                        break;
                    case "count":
                        StdOut.println("Stack count: " + stack.count());
                        break;
                    case "print":
                        stack.print();
                        break;
                    case "exit":
                        throw new Exception("Exiting program...");
                    default:
                        StdOut.println("Wrong argument!");
                        StdOut.println("API: { push <item>, pop, count, print, exit }\n");
                }
            } catch (Exception e) {
                StdOut.println(e.getMessage());
                break;
            }
        }
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayIterator();
    }

    private class ResizingArrayIterator implements Iterator<Item> {
        int i = 0;

        public boolean hasNext() {
            return s[i] != null;
        }

        public Item next() {
            Item item = s[i];
            i++;
            return item;
        }
    }
}
