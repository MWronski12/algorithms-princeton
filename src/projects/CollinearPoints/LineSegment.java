package CollinearPoints;

import edu.princeton.cs.algs4.StdOut;

public class LineSegment {
    public final Point p;
    public final Point q;

    public LineSegment(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    public String toString() {
        return "(" + p.toString() + ", " + q.toString() + ")";
    }

    static public void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 5);
        LineSegment l = new LineSegment(p1, p2);
        StdOut.println(l);
        l.draw();
    }
}
