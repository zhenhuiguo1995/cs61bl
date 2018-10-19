public class Max {
    /** Returns the maximum value from arr. */
    public static int max(int[] arr) {
        int temp = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > temp){
                temp = arr[i];
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}