import java.util.ArrayList;
import java.util.List;

public class BonusDates {
    public static final int END_MONTH = 12;
    public static final int END_DAY = 31;
    public static final ArrayList<Integer> MONTHS_WITH_30DAYS = new ArrayList<>(List.of(4, 6, 9, 11));
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please enter two arguments");
        }
        int fromYear = parseInt(args[0]);
        int toYear = parseInt(args[1]);
        if (fromYear <= 0 || fromYear >= toYear) {
            throw new IllegalArgumentException("Years must be larger than 0"
                    + "First argument has to be smaller than the second");
        }
        printBonusDatesBetween(fromYear, toYear);
    }

    private static int parseInt(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Arguments must be numbers");
        }
    }

    /**
     * method that prints all dates that remains the same if the numbers of the date are reversed
     *
     * @param fromYear - must be no less than 1
     * @param toYear   - must be larger than fromYear and no less than 1
     */
    public static void printBonusDatesBetween(int fromYear, int toYear) {
        if (fromYear <= 0 || fromYear >= toYear) {
            throw new IllegalArgumentException("Years must be larger than 0"
                    + "First argument has to be smaller than the second");
        }
        int startYear = fromYear;
        int startMonth = 1;
        int startDay = 1;
        int endYear = toYear - 1;
        String dateToCheck = startYear + String.format("%02d%02d", startMonth, startDay);

        while (startYear < endYear || startMonth != END_MONTH || startDay != END_DAY) {
            String year = dateToCheck.substring(0, dateToCheck.length()-4);
            String month = dateToCheck.substring(dateToCheck.length()-4, dateToCheck.length()-2);
            String day = dateToCheck.substring(dateToCheck.length()-2);

           if (year.length()>3 && (year.charAt(1) > '3' || year.charAt(3) > '1')){
               startYear = Integer.parseInt(year);
               startMonth = END_MONTH;
               startDay = END_DAY;
           } else {
               if (isPalindrome(dateToCheck)) {
                   System.out.println(year + "-" + month + "-" + day);
               }
               startYear = Integer.parseInt(year);
               startMonth = Integer.parseInt(month);
               startDay = Integer.parseInt(day);
           }

            dateToCheck = getNextDate(startYear, startMonth, startDay);
        }
    }

    /**
     * this method checks if the given String is a palindrome (a sequence that is the same backwards as it is
     * forwards)
     *
     * @param str - any string to check. Keep in mind that this method is case-sensitive
     * @return - true if it is a palindrome
     */
    public static boolean isPalindrome(String str) {
        if (str.length() <= 1) {
            return true;
        } else if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrome(str.substring(1, str.length() - 1));
        } else {
            return false;
        }
    }

    /**
     * function to calculate next day after given date in a String format
     *
     * @param year  - must be no less than 1
     * @param month - must be no less than one and no more than 12
     * @param day   - must be no less than 1 and no more than days in a month
     * @return - the next day's date in string format
     */
    public static String getNextDate(int year, int month, int day) {
        int daysInMonth = getDaysInMonth(year, month);
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > daysInMonth) {
            throw new IllegalArgumentException();
        }
        if (month == END_MONTH && day == daysInMonth) {
            year++;
            month = 1;
            day = 1;
        } else if (day == daysInMonth) {
            month++;
            day = 1;
        } else {
            day++;
        }

        return year + String.format("%02d%02d", month, day);
    }

    /**
     * function to calculate the number of days in a month at a certain year
     *
     * @param year  - the given year (if the given year is leap year, number of days in february changes)
     * @param month - the month to calculate days in
     * @return - the number of days in given month in given year
     */
    public static int getDaysInMonth(int year, int month) {
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException();
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else if (MONTHS_WITH_30DAYS.contains(month)) {
            return 30;
        } else {
            return 31;
        }
    }

    /**
     * function to determine if the given year is a leap year
     *
     * @param year - must be no more than 1
     * @return - true if it is a leap year
     */
    public static boolean isLeapYear(int year) {
        if (year < 1) {
            throw new IllegalArgumentException();
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
