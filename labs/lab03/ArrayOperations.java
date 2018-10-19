public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        } else {
            int k = pos;
            while (k < values.length - 1){
                values[k] = values[k + 1];
                k += 1;
            }
            values[k] = 0;
        }
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        } else {
            int k = pos;
            int pre = newInt;
            int temp = 0;
            while (k < values.length - 1){
                temp = values[k];
                values[k] = pre;
                pre = temp;
                k += 1;
            }
            values[k] = pre;
        }
    }
}
