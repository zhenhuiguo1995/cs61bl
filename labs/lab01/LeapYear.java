/**
 * Class that determines whether or not a year is a leap year.
 * @author Alfred
 */
public class LeapYear {
    /**
     * Representing a year.
     */
    public static final int FOURHUNDRED = 400;
    /**
     * Takes an integer and decides if it is a leap year.
     * @return
     * @param year ** An integer representing the year.
     */
    public static boolean isLeapYear(int year) {
        if (year % FOURHUNDRED == 0) {
            return true;
        } else {
            if (year % 4 == 0 && year % 100 != 0) {
                return true;
            }
            return false;
        }
    }

    /** Calls isLeapYear to print correct statement.
     * @param year ** An integer representing the year.
     * */
    private static void checkLeapYear(int year) {
        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year.\n", year);
        } else {
            System.out.printf("%d is not a leap year.\n", year);
        }
    }

    /**
     * @param args ** This is a command line argument
     * Must be provided an integer as a command line argument ARGS. */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java LeapYear 2000");
        }
        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                checkLeapYear(year);
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}
