import java.util.Arrays;

public class WeightedQuickUnionUF {

    private int count;
    private int[] id;
    private int[] size;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int p) {

        while (p != id[p]) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {

        int i = root(p);
        int j = root(q);

        if (i == j)
            return;

        if (size[j] > size[i]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }

    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(7, 3);

        System.out.println(Arrays.toString(uf.id));
        System.out.println(Arrays.toString(uf.size));
        System.out.println(uf.connected(1, 9));
    }
}
