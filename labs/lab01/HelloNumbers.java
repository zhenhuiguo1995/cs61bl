/**@author Alfred **Prints out consecutive numbers.*/
public class HelloNumbers {
    /** @param args ** This is the command line arguments.*/
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
        System.out.println();
    }
}
