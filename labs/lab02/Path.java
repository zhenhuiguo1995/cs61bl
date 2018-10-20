/** A class that represents a path via pursuit curves.
 * @author Alfred
 * */
public class Path {
    /**
     * The current point.
     */
    public Point curr;
    /**
     * The next point.
     */
    public Point next;

    /**
     * Set initial path of curr and next.
     * @param x ** The x coordinates.
     * @param y ** The y coordinates.
     */
    public Path(double x, double y) {
        this.curr = new Point(x, y);
        this.next = new Point(100, 100);
    }

    /**
     * Change next and curr so that it forms a path.
     * @param dx ** Increment in the x direction.
     * @param dy ** Increment in the y direction.
     */
    void iterate(double dx, double dy) {
        this.curr = this.next;
        this.next.x += dx;
        this.next.y += dy;
    }
}
