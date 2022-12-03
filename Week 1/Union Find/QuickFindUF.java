import java.io.Console;
import java.util.Arrays;

public class QuickFindUF {

    public int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {

        QuickFindUF uf = new QuickFindUF(10);
        System.out.println(Arrays.toString(uf.id));
        uf.union(2, 1);
        System.out.println(Arrays.toString(uf.id));
        uf.union(3, 2);
        System.out.println(Arrays.toString(uf.id));
    }
}