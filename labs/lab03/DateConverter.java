import java.io.*;

public class DateConverter {
    /**
     * Given a day number in 2017, an integer between 1 and 365, as a
     * command-line argument, prints the date in month/day format.
     *
     *     java DateConverter 365
     *
     * should print 12/31
     */
    public static void main(String[] args) {
        int dayOfYear = 0;
        try {
            dayOfYear = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int month, dateInMonth, daysInMonth;
        month = 1;
        daysInMonth = 31;
        while (dayOfYear > daysInMonth) {
            dayOfYear -= daysInMonth;
            month += 1;
            if (month == 2) {
                daysInMonth = 28;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }
        }
        dateInMonth = dayOfYear;
        System.out.println(month + "/" + dateInMonth);
    }
}
