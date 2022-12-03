import java.util.Arrays;
import java.util.Scanner;

public class Exc {

    private int count;
    private int[] id;
    private int[] size;
    private int[] successors;

    public Exc(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        successors = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            successors[i] = i;
        }
    }

    private int root(int p) {

        while (p != id[p]) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    private void union(int p, int q) {

        int i = root(p);
        int j = root(q);

        if (i == j)
            return;

        if (size[i] <= size[j]) {
            id[i] = id[j];
            size[j] += size[i];
        } else {
            id[j] = id[i];
            size[i] += size[j];
        }

        successors[i] = j;
    }

    public int count() {
        return count;
    }

    public void remove(int x) {
        if (x == count - 1) {
            return;
        }

        union(x, root(x + 1));
    }

    public int successor(int x) {
        return successors[root(x)];
    }

    private void printInstruction() {
        System.out.println("Available commands:\n\n- remove <x>\n- successor <x>\n- print\n");
    }

    private int validateArgument(int argument) {
        if (argument < 0 || argument > count - 1)
            return -1;

        return 1;
    }

    public static void main(String[] args) {
        int n;
        Exc uf;

        Scanner scan = new Scanner(System.in);
        System.out.println("Pass the size of the set:");
        n = scan.nextInt();
        uf = new Exc(n);

        uf.printInstruction();

        String command;
        Integer argument = -1;
        // String[] input = new String[] { "", "" };
        while (true) {

            System.out.println("Waiting for command:");
            try {
                command = scan.next();

                if (!command.equals("print")) {
                    argument = scan.nextInt();
                    if (uf.validateArgument(argument) != 1) {
                        System.out.println("Wrong argument!");
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("An exception occured: " + e.getMessage());
                continue;
            }

            if (command.equals("remove")) {
                uf.remove(argument);
                System.out.println(argument + " was removed from the set!\n");
            } else if (command.equals("successor")) {
                System.out.println("The successor of " + argument + " is " + uf.successor(argument));
            } else if (command.equals("print")) {
                System.out.println("id: " + Arrays.toString(uf.id));
                System.out.println("successors: " + Arrays.toString(uf.successors));
            } else if (command.equals("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Wrong command!\n");
                uf.printInstruction();
            }
        }

        scan.close();
    }
}
