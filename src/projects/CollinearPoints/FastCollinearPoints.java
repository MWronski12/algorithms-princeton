package CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private final Point[] points;

    // Constructor - make sure no null Points were passed and initialize points array
    public FastCollinearPoints(Point[] points) {
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Null point found in points array!");
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

        for (Point point : points) {
            Comparator<Point> comparator = point.slopeOrder();
            Arrays.sort(points, comparator);
            LineSegment l = GetLineSegmentFromSortedPointsArray(points, comparator);
            if (l != null) {
                lineSegments[numOfLineSegments++] = l;
            }
        }
        return Arrays.copyOf(lineSegments, numOfLineSegments);
    }

    private LineSegment GetLineSegmentFromSortedPointsArray(Point[] points, Comparator<Point> comparator) {
        int i = 0;
        while (i < points.length - 1 && comparator.compare(points[i], points[i + 1]) == 0) i++;
        if (i >= 4) return new LineSegment(points[0], points[i]);
        return null;
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

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }

        StdOut.println(Arrays.toString(collinear.segments()));
    }
}
