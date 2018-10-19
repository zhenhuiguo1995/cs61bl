public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
        for(int i = 0; i < a.length; i ++){
            if(a[i] < 0){
                continue;
            }else {
                int j = 1;
                while ((j < (n + 1)) && (i + j) < a.length) {
                    a[i] += a[i + j];
                    j += 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}