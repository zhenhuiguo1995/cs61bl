/**
 * @author Alfred
 * Get the max value from an int arr.
 */
public class Max {
    /**Returns the maximum value from arr.
     * @param arr ** An int array.
     * */
    public static int max(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > temp) {
                temp = arr[i];
            }
        }
        return temp;
    }

    /**
     * The main method.
     * @param args ** A command line argument.
     */
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
