package CollinearPoints;

import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {
    private final Point[] points;

    // Make sure no null Points were passed and initialize points array
    public BruteCollinearPoints(Point[] points) {
        for (Point p : points) {
            if (p == null)
                throw new IllegalArgumentException("Null point found in points array!");
        }
        this.points = Arrays.copyOf(points, points.length);
    }

    // Get number of LineSegments that contain 4 or more collinear points
    public int numberOfSegments() {
        return segments().length;
    }

    // Get all LineSegments that contain 4 or more collinear points
    public LineSegment[] segments() {

        LineSegment[] lineSegments = new LineSegment[points.length];
        int numOfLineSegments = 0;

        // Iterate over the array keeping 3 indices i, j, k
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {

                // Create placeholder for collinear points and add reference points
                LinkedList<Point> collinearPoints = new LinkedList<>();
                collinearPoints.add(points[i]);
                collinearPoints.add(points[j]);

                // Check for other collinear points to i-j LineSegment
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeOrder().compare(points[j], points[k]) == 0)
                        collinearPoints.add(points[k]);
                }

                // We are looking only for line segments containing 4 or more Points
                if (collinearPoints.size() < 4)
                    continue;

                // Find min and max collinear points and create the LineSegment
                Point min = collinearPoints.getFirst();
                Point max = collinearPoints.getFirst();
                for (Point p : collinearPoints) {
                    if (p.compareTo(min) < 0)
                        min = p;
                    if (p.compareTo(max) > 0)
                        max = p;
                }
                lineSegments[numOfLineSegments++] = new LineSegment(min, max);
            }
        }
        return Arrays.copyOf(lineSegments, numOfLineSegments);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.setPenRadius();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
