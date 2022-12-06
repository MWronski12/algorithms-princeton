package InterviewQuestions;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

class StackWithMaxElement<Item> extends Stack<Item> implements Iterable<Item> {

    private Item max;

    public Item findMax() {
        return max;
    }

    public void push(Item item) {
        if (this.size() == 0 || (double) item > (double) max) {
            max = item;
        }
        super.push(item);
    }

    public Item pop() {
        Item item = super.pop();

        if (Double.compare((double) item, (double) max) == 0) {
            Item newMax = super.peek();

            for (Item i : this) {
                if ((double) i > (double) newMax) {
                    newMax = i;
                }
            }
            max = newMax;
        }
        return item;
    }

    public static void main(String[] args) {

        StackWithMaxElement<Double> s = new StackWithMaxElement<>();
        s.push(1.0);
        s.push(12.0);
        s.push(5.0);
        s.push(28.0);
        s.push(9.0);
        StdOut.println("Max: " + s.findMax());
        s.pop();
        s.pop();
        StdOut.println("Max: " + s.findMax());
    }
}
