package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation percolation;
    private final int size;
    private final int numOfTrials;
    int[] randomOrderOfSites;
    double percolationThreshold;
    double[] results;
    int index = 0;
    int[] matrixElement;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        results = new double[trials];
        size = n;
        numOfTrials = trials;

        randomOrderOfSites = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            randomOrderOfSites[i] = i;
        }

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            StdRandom.shuffle(randomOrderOfSites);
            index = 0;

            while (!percolation.percolates()) {
                matrixElement = arrayIndexToMatrixElement(randomOrderOfSites[index]);
                percolation.open(matrixElement[0], matrixElement[1]);
                index++;
            }

            percolationThreshold = (double) (index) / (double) (size * size);
            results[i] = percolationThreshold;
        }
    }

    private int[] arrayIndexToMatrixElement(int i) {
        int row = i / size + 1;
        int col = i % size + 1;
        return new int[]{row, col};
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numOfTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numOfTrials);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("PercolationStats <n> <trials>");
        }

        int n;
        int trials;

        try {
            n = Integer.parseInt(args[1]);
            trials = Integer.parseInt(args[2]);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        // print formatted mean
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.print("mean");
        for (int i = 0; i < "95% confidence interval".length() - "mean".length(); i++) {
            StdOut.print(" ");
        }
        StdOut.println(" = " + percolationStats.mean());

        // print formatted stddev
        StdOut.print("stddev");
        for (int i = 0; i < "95% confidence interval".length() - "stddev".length(); i++) {
            StdOut.print(" ");
        }
        StdOut.println(" = " + percolationStats.stddev());

        // print 95% confidence interval
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
