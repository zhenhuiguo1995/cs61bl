public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    @Override
    public Date nextDate() {
        int year, month, dayOfMonth;
        if (this.dayOfMonth + 1 > getMonthLength(this.month)) {
            if (this.month + 1 > MONTH_LENGTHS.length) {
                year = this.year + 1;
                month = 1;
                dayOfMonth = 1;
            } else {
                year = this.year;
                month = this.month + 1;
                dayOfMonth = 1;
            }
        } else {
            year = this.year;
            month = this.month;
            dayOfMonth = this.dayOfMonth + 1;
        }
        return new GregorianDate(year, month, dayOfMonth);
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}
