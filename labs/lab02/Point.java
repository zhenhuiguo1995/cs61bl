/** A point in 2D space.
 * @author Alfred
 * */
public class Point {
    /**The x coordinates of a point object.*/
    public double x;
    /**The Y coordinates of a point object.*/
    public double y;

    /**A constructor that returns a point at the origin. */
    Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * @param xx ** The x coordinates of a point.
     * @param yy ** The y coordinates of a point.
     * A constructor that takes in the x, y coordinate of a point. */
    Point(double xx, double yy) {
        this.x = xx;
        this.y = yy;
    }

    /**@param other ** A point object.
     * A constructor that creates a point with the same x and y values. */
    Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
