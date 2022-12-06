package Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final int size;
    private final int[] siteVacancyArray;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        uf = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        siteVacancyArray = new int[n * n];
        virtualTop = n * n;
        virtualBottom = n * n + 1;

        for (int i = 0; i < n * n; i++) {
            siteVacancyArray[i] = 0;
            if (i < n) {
                uf.union(i, virtualTop);
            } else if (i >= n * n - n) {
                uf.union(i, virtualBottom);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateMatrixElementArguments(row, col);
        if (isOpen(row, col)) {
            return;
        }
        siteVacancyArray[matrixElementToArrayIndex(row, col)] = 1;
        connectAdjacentSites(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateMatrixElementArguments(row, col);
        return siteVacancyArray[matrixElementToArrayIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateMatrixElementArguments(row, col);
        if (isOpen(row, col)) {
            return uf.find(virtualTop) == uf.find(matrixElementToArrayIndex(row, col));
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size * size; i++) {
            if (siteVacancyArray[i] == 1) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // connects adjacent sites after opening a site
    private void connectAdjacentSites(int row, int col) {
        connectSiteOnTheLeft(row, col);
        connectSiteOnTheRight(row, col);
        connectSiteOnTheTop(row, col);
        connectSiteOnTheBottom(row, col);
    }


    private void connectSiteOnTheLeft(int row, int col) {
        if (col == 1) {
            return;
        }
        if (isOpen(row, col - 1)) {
            int siteIndex = matrixElementToArrayIndex(row, col);
            int leftSiteIndex = matrixElementToArrayIndex(row, col - 1);
            uf.union(siteIndex, leftSiteIndex);
        }
    }

    private void connectSiteOnTheRight(int row, int col) {
        if (col == size) {
            return;
        }
        if (isOpen(row, col + 1)) {
            int siteIndex = matrixElementToArrayIndex(row, col);
            int rightSiteIndex = matrixElementToArrayIndex(row, col + 1);
            uf.union(siteIndex, rightSiteIndex);
        }
    }

    private void connectSiteOnTheTop(int row, int col) {
        if (row == 1) {
            return;
        }
        if (isOpen(row - 1, col)) {
            int siteIndex = matrixElementToArrayIndex(row, col);
            int topSiteIndex = matrixElementToArrayIndex(row - 1, col);
            uf.union(siteIndex, topSiteIndex);
        }
    }

    private void connectSiteOnTheBottom(int row, int col) {
        if (row == size) {
            return;
        }
        if (isOpen(row + 1, col)) {
            int siteIndex = matrixElementToArrayIndex(row, col);
            int bottomSiteIndex = matrixElementToArrayIndex(row + 1, col);
            uf.union(siteIndex, bottomSiteIndex);
        }
    }

    public int matrixElementToArrayIndex(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    private void validateMatrixElementArguments(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("Arguments outside of the matrix range!");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("Pass size of the grid:");
        String n = StdIn.readString();
        StdOut.println("Pass number of trials:");
        String trial = StdIn.readString();
        PercolationStats.main(new String[] {"PercolationStats", n, trial});
    }
}
