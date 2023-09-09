public class BonusDates {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please enter two arguments");
        }
        int fromYear = parseInt(args[0]);
        int toYear = parseInt(args[1]);
        if (toYear > 9999 || fromYear < 1000 || fromYear >= toYear) {
            throw new IllegalArgumentException("Valid arguments only between 1000 and 9999. " +
                    "First argument has to be smaller than the second");
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
        int startYear = fromYear;
        int startMonth = 1;
        int startDay = 1;
        int endYear = toYear - 1;
        int endMonth = 12;
        int endDay = 31;
        String dateToCheck = String.format("%04d%02d%02d", startYear, startMonth, startDay);

        while (startYear < endYear || startMonth != endMonth || startDay != endDay) {
            if (isPalindrome(dateToCheck)) {
                System.out.println(dateToCheck.substring(0, 4) + "-" + dateToCheck.substring(4, 6)
                        + "-" + dateToCheck.substring(6));
            }
            startYear = Integer.parseInt(dateToCheck.substring(0, 4));
            startMonth = Integer.parseInt(dateToCheck.substring(4, 6));
            startDay = Integer.parseInt(dateToCheck.substring(6));
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
        if (month == 12 && day == daysInMonth) {
            year++;
            month = 1;
            day = 1;
        } else if (day == daysInMonth) {
            month++;
            day = 1;
        } else {
            day++;
        }

        return String.format("%04d%02d%02d", year, month, day);
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
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
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
