/** A class that represents a path via pursuit curves. */
public class Path {
    public Point curr;
    public Point next;

    public Path(double x, double y){
        this.curr = new Point(x, y);
        this.next = new Point(100, 100);
    }

    void iterate(double dx, double dy){
        this.curr = this.next;
        this.next.x += dx;
        this.next.y += dy;
    }
}
