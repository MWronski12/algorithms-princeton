package CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FastCollinearPoints {

    private Point[] points;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        checkForNullPoints(points);
        checkForDuplicatePoints(points);
        this.points = Arrays.copyOf(points, points.length);
    }

    // Get number of LineSegments that contain 4 or more collinear points
    public int numberOfSegments() {
        if (this.segments == null) {
            return this.segments().length;
        }
        return this.segments.length;
    }

    // Get all LineSegments that contain 4 or more collinear points
    public LineSegment[] segments() {

        Arrays.sort(this.points);
        Point[] aux = Arrays.copyOf(this.points, this.points.length);

        HashMap<Point, Point> lineSegmentsMap = new HashMap<>();

        for (Point origin : points) {
            
            // Stable sort the array in respect to the slope points make with origin
            Comparator<Point> comparator = origin.slopeOrder();
            Arrays.sort(aux);
            Arrays.sort(aux, comparator);

            int i = 1; // Skip aux[0] which is origin
            int numOfCollinearPoints = 2; // Origin and 1st in series

            // Find series of adjecent points with equal slopes in respect to origin
            while (i < aux.length) {

                if (i == aux.length - 1 || origin.slopeTo(aux[i]) != origin.slopeTo(aux[i + 1])) {
                    // Last collinear point in a series was reached
                    if (numOfCollinearPoints >= 4
                            && lineSegmentsMap.get(origin) == null
                            && lineSegmentsMap.get(aux[i]) == null) {
                        lineSegmentsMap.put(aux[i], origin);
                    }
                    numOfCollinearPoints = 2;

                } else {
                    numOfCollinearPoints++;
                }

                i++;
            }
        }

        this.segments = hashMapToArray(lineSegmentsMap);
        return this.segments;
    }

    private LineSegment[] hashMapToArray(HashMap<Point, Point> map) {
        int size = map.size();
        LineSegment[] segments = new LineSegment[size];
        int i = 0;
        for (Map.Entry<Point, Point> entry : map.entrySet()) {
            segments[i++] = new LineSegment(entry.getValue(), entry.getKey());
        }
        return segments;
    }

    private void checkForNullPoints(Point[] points) {
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Found null point in the array!");
            }
        }
    }

    private void checkForDuplicatePoints(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Found duplicate points in the array!");
            }
        }
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
