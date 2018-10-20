/**
 * @author Alfred
 * Draw a Triangle.
 */
public class Draw {
    /**
     * Draw a triangle.
     * @param n ** A user-defined argument
     */
    public static void drawTriangle(int n) {
        int col = 0;
        while (col < n) {
            int i = 0;
            while (i <= col) {
                System.out.print("*");
                i += 1;
            }
            col += 1;
            System.out.println();
        }
    }

    /**
     * Invokes the DrawTriangle method and draws a triangle.
     * @param args ** A command line argument.
     */
    public static void main(String[] args) {
        drawTriangle(8);
    }
}
