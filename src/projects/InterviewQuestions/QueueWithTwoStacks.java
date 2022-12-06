package InterviewQuestions;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

public class QueueWithTwoStacks<Item> {
    ResizingArrayStack<Item> enqueueStack = new ResizingArrayStack<>();
    ResizingArrayStack<Item> dequeueStack = new ResizingArrayStack<>();

    public int size() {
        return enqueueStack.size() + dequeueStack.size();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public void enqueue(Item item) {
        enqueueStack.push(item);
    }

    public Item dequeue() {
        if (!dequeueStack.isEmpty()) {
            return dequeueStack.pop();
        } else {
            int enqueueStackSize = enqueueStack.size();
            for (int i = 0; i < enqueueStackSize; i++) {
                dequeueStack.push(enqueueStack.pop());
            }
            return dequeueStack.pop();
        }
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<String> q = new QueueWithTwoStacks<>();
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.enqueue("4");
        q.enqueue("5");
        q.dequeue();
        q.enqueue("6");
        q.dequeue();
        q.enqueue("7");
        q.enqueue("8");
        q.dequeue();
        StdOut.println("Enqueue stack:");
        for (String item : q.enqueueStack) {
            StdOut.print(item + " ");
        }
        StdOut.println();
        StdOut.println("Dequeue stack:");
        for (String item : q.dequeueStack) {
            StdOut.print(item + " ");
        }
    }
}
