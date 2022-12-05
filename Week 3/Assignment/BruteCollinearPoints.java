package Assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class BruteCollinearPoints {
    private final Point[] points;

    // Constructor - make sure no null Points were passed and initialize points array
    public BruteCollinearPoints(Point[] points) {
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

        // Placeholder array
        LineSegment[] lineSegments = new LineSegment[points.length];
        int lineSegmentsIndex = 0;

        // Iterate over the array keeping 3 indices i,j, k
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {

                // Create placeholder for collinear points and add reference points
                LinkedList<Point> collinearPoints = new LinkedList<>();
                collinearPoints.add(points[i]);
                collinearPoints.add(points[j]);

                // Check for other collinear points to i-j LineSegment
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeOrder().compare(points[j], points[k]) == 0) collinearPoints.add(points[k]);
                }

                // We are looking only for line segments containing 4 or more Points
                if (collinearPoints.size() < 4) continue;

                // Find min and max collinear points and create the LineSegment
                Point min = collinearPoints.getFirst();
                Point max = collinearPoints.getFirst();
                for (Point p : collinearPoints) {
                    if (p.compareTo(min) < 0) min = p;
                    if (p.compareTo(max) > 0) max = p;
                }
                lineSegments[lineSegmentsIndex++] = new LineSegment(min, max);
            }
        }
        return Arrays.copyOf(lineSegments, lineSegmentsIndex);
    }

    public static void main(String[] args) {
        Point[] points = {new Point(0, 0), new Point(5, 5), new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(1, 1), new Point(-8, -8)};
        BruteCollinearPoints BCP = new BruteCollinearPoints(points);
        StdOut.println("There are " + BCP.numberOfSegments() + " LineSegments containing 4 or more collinear points:");
        StdOut.println(Arrays.toString(BCP.segments()));
    }
}
