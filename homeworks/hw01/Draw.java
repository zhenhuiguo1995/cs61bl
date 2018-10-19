public class Draw {
    public static void DrawTriangle(int n){
        int col = 0;
        while (col < n){
            int i = 0;
            while (i <= col){
                System.out.print("*");
                i += 1;
            }
            col += 1;
            System.out.println();
        }
    }


    public static void main(String[] args) {
        DrawTriangle(8);
    }
}