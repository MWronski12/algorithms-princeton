package CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private final Point[] points;

    // Make sure no null Points were passed and initialize points array
    public FastCollinearPoints(Point[] points) {
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

        Point[] aux = Arrays.copyOf(this.points, this.points.length);

        for (Point referencePoint : points) {
            // Sort the array in respect to the slope they make with referencePoint
            Comparator<Point> comparator = referencePoint.slopeOrder();
            Arrays.sort(aux, comparator);

            int i = 1; // slope of the same points is negative infinity -> aux[0] = referencePoint
            int numOfCollinearPoints = 2; // must be at least 2 points
            Point min = referencePoint;
            Point max = referencePoint;

            while (i < aux.length) { // Find series of adjecent collinear points

                // Keep track of min and max to consider only the longest LineSegment
                if (aux[i].compareTo(min) < 0)
                    min = aux[i];
                if (aux[i].compareTo(max) > 0)
                    max = aux[i];

                if (i != aux.length - 1 && referencePoint.slopeTo(aux[i]) == referencePoint.slopeTo(aux[i + 1])) {
                    numOfCollinearPoints++;
                } else {
                    // Last collinear point in a series was reached
                    if (numOfCollinearPoints >= 4) {
                        LineSegment newLineSegment = new LineSegment(min, max);
                        if (LineSegmentIsNew(lineSegments, numOfLineSegments, newLineSegment))
                            lineSegments[numOfLineSegments++] = newLineSegment;
                    }
                    // Reset indicators
                    min = referencePoint;
                    max = referencePoint;
                    numOfCollinearPoints = 2;
                }
                i++;
            }
        }
        return Arrays.copyOf(lineSegments, numOfLineSegments);
    }

    private boolean LineSegmentIsNew(LineSegment[] lineSegments, int numOfLineSegments, LineSegment newLineSegment) {
        boolean isNew = true;
        for (int i = 0; i < numOfLineSegments; i++) {
            if (newLineSegment.p.compareTo(lineSegments[i].p) == 0
                    && newLineSegment.q.compareTo(lineSegments[i].q) == 0) {
                isNew = false;
                break;
            }
        }
        return isNew;
    }

    public static void main(String[] args) {
        // Read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // Draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.setPenRadius();

        // Print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
