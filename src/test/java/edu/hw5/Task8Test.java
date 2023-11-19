package edu.hw5;

import org.junit.jupiter.api.Test;
import edu.hw5.task8.BinaryRegexMatcher;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    void checkRuleWork1() {
        String correctString = "10101";
        String incorrectString = "1111";

        boolean correctAnswer = BinaryRegexMatcher.checkRule1(correctString);
        boolean incorrectAnswer = BinaryRegexMatcher.checkRule1(incorrectString);

        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    void checkRuleWork2() {
        String correctString1 = "01100";
        String incorrectString1 = "0000";
        String correctString2 = "1001";
        String incorrectString2 = "100";

        boolean correctAnswer1 = BinaryRegexMatcher.checkRule2(correctString1);
        boolean incorrectAnswer1 = BinaryRegexMatcher.checkRule2(incorrectString1);
        boolean correctAnswer2 = BinaryRegexMatcher.checkRule2(correctString2);
        boolean incorrectAnswer2 = BinaryRegexMatcher.checkRule2(incorrectString2);

        assertTrue(correctAnswer1);
        assertFalse(incorrectAnswer1);
        assertTrue(correctAnswer2);
        assertFalse(incorrectAnswer2);
    }

    @Test
    void checkRuleWork3() {
        String correctString = "1000111011100100011";
        String incorrectString = "11111001100011";

        boolean correctAnswer = BinaryRegexMatcher.checkRule3(correctString);
        boolean incorrectAnswer = BinaryRegexMatcher.checkRule3(incorrectString);

        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    void checkRuleWork4() {
        String correctString = "11000101001001010";
        String correctString2 = "111010101";
        String incorrectString = "111";
        String incorrectString2 = "11";

        boolean correctAnswer = BinaryRegexMatcher.checkRule4(correctString);
        boolean correctAnswer2 = BinaryRegexMatcher.checkRule4(correctString2);
        boolean incorrectAnswer = BinaryRegexMatcher.checkRule4(incorrectString);
        boolean incorrectAnswer2 = BinaryRegexMatcher.checkRule4(incorrectString2);

        assertTrue(correctAnswer);
        assertTrue(correctAnswer2);
        assertFalse(incorrectAnswer);
        assertFalse(incorrectAnswer2);
    }

    @Test
    void checkRuleWork5() {
        String correctString = "101110111010";
        String incorrectString = "0110111011";

        boolean correctAnswer = BinaryRegexMatcher.checkRule5(correctString);
        boolean incorrectAnswer = BinaryRegexMatcher.checkRule5(incorrectString);

        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    void checkRuleWork7() {
        String correctString = "1000010000010101";
        String incorrectString = "000110010010001";

        boolean correctAnswer = BinaryRegexMatcher.checkRule7(correctString);
        boolean incorrectAnswer = BinaryRegexMatcher.checkRule7(incorrectString);

        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }
}
