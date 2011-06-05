package test.stringcalculator;

import org.junit.Test;
import stringcalculator.StringCalculator;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {
    @Test
    public void returnZeroIfEmptyStringIsPassed() {
        int result = new StringCalculator().add("");
        assertThat("Calculated result", result, is(0));
    }

    @Test
    public void returnOneAsIntegerIfOneAsStringIsPassed() {
        int result = new StringCalculator().add("1");
        assertThat("Calculated result", result, is(1));
    }

    @Test
    public void returnTwoAsIntegerIfTwoAsStringIsPassed() {
        int result = new StringCalculator().add("2");
        assertThat("Calculated result", result, is(2));
    }

    @Test
    public void returnThreeIfTwoSingleDigitNumbersNamelyOneAndTwoSeparatedByCommaArePassed() {
        int result = new StringCalculator().add("1,2");
        assertThat("Calculated result", result, is(3));
    }

    @Test
    public void returnFiveIfTwoSingleDigitNumbersNamelyTwoAndThreeSeparatedByCommaArePassed() {
        int result = new StringCalculator().add("2,3");
        assertThat("Calculated result", result, is(5));
    }

    @Test
    public void returnThirtyIfTwoDoubleDigitNumbersNamelyTenAndTwentySeparatedByCommaArePassed() {
        int result = new StringCalculator().add("10,20");
        assertThat("Calculated result", result, is(30));
    }

    @Test public void
    returnThirtyOneIfTwoDoubleDigitNumbersNamelyTenAndTwentyAndOneSingleDigitNumberNamelyOneSeparatedByCommaArePassed() {
        int result = new StringCalculator().add("10,20,1");
        assertThat("Calculated result", result, is(31));
    }

    @Test
    public void returnThreeIfTwoSingleDigitNumbersNamelyTwoAndThreeSeparatedByNewLineArePassed() {
        int result = new StringCalculator().add("1\n2");
        assertThat("Calculated result", result, is(3));
    }

    @Test
    public void returnSevenIfTwoSingleDigitNumbersNamelyThreeAndFourSeparatedByNewLineArePassed() {
        int result = new StringCalculator().add("3\n4");
        assertThat("Calculated result", result, is(7));
    }

    @Test
    public void returnThreeIfTwoSingleDigitNumbersNamelyOneAndTwoSeparatedByCustomDelimiterNamelySemicolonArePassed() {
        int result = new StringCalculator().add("//;\n1;2");
        assertThat("Calculated result", result, is(3));
    }

    @Test
    public void returnFiveIfTwoSingleDigitNumbersNamelyTwoAndThreeSeparatedByCustomDelimiterNamelySemicolonArePassed() {
        int result = new StringCalculator().add("//;\n2;3");
        assertThat("Calculated result", result, is(5));
    }

    @Test
    public void returnFiveIfTwoSingleDigitNumbersNamelyTwoAndThreeSeparatedByCustomDelimiterNamelyAtSignArePassed() {
        int result = new StringCalculator().add("//@\n2@3");
        assertThat("Calculated result", result, is(5));
    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionIfNegativeNumberIsPassed() {
        new StringCalculator().add("-1");
    }

    @Test
    public void reportNegativeIfNegativeANumberIsPassed() {
        try {
            new StringCalculator().add("-1");
            fail("Must throw Exception if negative number is passed.");
        } catch (Exception e) {
            assertThat("Error message", e.getMessage(), is(equalTo("Negatives not allowed: -1")));
        }
    }

    @Test
    public void reportOnlyNegativeIfMixtureOfPositiveAndNegativeNumbersArePassed() {
        try {
            new StringCalculator().add("2,3,4,-1,7,8,-7");
            fail("Must throw Exception if negative number is passed.");
        } catch (Exception e) {
            assertThat("Error message", e.getMessage(), is(equalTo("Negatives not allowed: -1 -7")));
        }
    }


}