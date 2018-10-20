/**
 * @author Alfred
 * Exercises on the usage of break and continue.
 */
public class BreakContinue {
    /**
     *
     * @param a ** An int array to add.
     * @param n ** The number of array elements to add.
     */
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                continue;
            } else {
                int j = 1;
                while ((j < (n + 1)) && (i + j) < a.length) {
                    a[i] += a[i + j];
                    j += 1;
                }
            }
        }
    }

    /**
     * The main method, which executes the windowPosSum method.
     * @param args ** The command line arguments.
     */
    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);
        System.out.println(java.util.Arrays.toString(a));
    }
}
