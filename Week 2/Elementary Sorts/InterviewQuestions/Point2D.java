package InterviewQuestions;

public class Point2D implements Comparable<Point2D> {
    int x;
    int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Comparing by x first
    public int compareTo(Point2D that) {
        if (this.x < that.x) return -1;
        else if (this.x > that.x) return 1;
        else if (this.y < that.y) return -1;
        else if (this.y > that.y) return 1;
        else return 0;
    }
}
