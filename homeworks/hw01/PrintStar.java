public class PrintStar{
    public static void main(String[] args){
        int col = 1;
        int size = 6;
        while (col < 6){
            int i = 0;
            while(i < col){
                System.out.print("*");
                i += 1;
            }
            System.out.println();
            col += 1;
        }
    }
}