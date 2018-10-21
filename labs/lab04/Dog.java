/** the dog class*/
public class Dog {
    private int size;

    /**Constructor a dog object with its size*/
    public Dog(int s) {
        size = s;
    }

    /** @return Makes a noise. */
    public String noise() {
        if (size < 10) {
            return "yip";
        }
        return "bark";
    }
}
