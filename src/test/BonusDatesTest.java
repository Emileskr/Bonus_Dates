import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BonusDatesTest {

    @Test
    public void twentyTwentyThreeIsNotALeapYear() {
        assertFalse(BonusDates.isLeapYear(2023));
    }

    @Test
    public void twentyTwentyIsALeapYear() {
        assertTrue(BonusDates.isLeapYear(2020));
    }

    @Test
    public void negativeYearThrowsException() {
        assertThrowsExactly(IllegalArgumentException.class, () -> BonusDates.isLeapYear(-2023));
    }

    @Test
    public void januaryHas31Day(){
        assertEquals(31,BonusDates.getDaysInMonth(2023, 1));
    }

    @Test
    public void februaryHas28DaysIn2023(){
        assertEquals(28,BonusDates.getDaysInMonth(2023, 2));
    }

    @Test
    public void februaryHas29DaysIn2020(){
        assertEquals(29,BonusDates.getDaysInMonth(2020, 2));
    }

    @Test
    public void ThirteenthMonthThrowsException(){
        assertThrowsExactly(IllegalArgumentException.class, () -> BonusDates.getDaysInMonth(2023, 13));
    }

    @Test
    public void rotorIsAPalindrome() {
        assertTrue(BonusDates.isPalindrome("rotor"));
    }

    @Test
    public void houseIsNotAPalindrome() {
        assertFalse(BonusDates.isPalindrome("house"));
    }

    @Test
    public void nextDateAfterFirstOfJanuaryIsSecondOfJanuary() {
        assertEquals("20230102", BonusDates.getNextDate(2023, 1, 1));
    }

    @Test
    public void nextDateAfterLastOfDecemberIsFirstOfJanuaryNextYear() {
        assertEquals("20230101", BonusDates.getNextDate(2022, 12, 31));
    }

    @Test
    public void nextDateAfterLastOfFebruaryIsFirstOfMarchIfNotLeapYear() {
        assertEquals("20220301", BonusDates.getNextDate(2022, 2, 28));
    }

    @Test
    public void After28thOfFebruaryIs29thOfFebruaryIfLeapYear() {
        assertEquals("20200229", BonusDates.getNextDate(2020, 2, 28));
    }

    @Test
    public void ThirtySecondDayThrowsException() {
        assertThrowsExactly(IllegalArgumentException.class, () -> BonusDates.getNextDate(2022, 3, 32));
    }

    @Test
    public void TwentyNinthDayOfFebruaryThrowsExceptionIfNotLeapYear() {
        assertThrowsExactly(IllegalArgumentException.class, () -> BonusDates.getNextDate(2022, 2, 29));
    }
}