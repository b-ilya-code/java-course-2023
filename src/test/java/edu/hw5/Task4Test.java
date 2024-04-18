package edu.hw5;

import org.junit.jupiter.api.Test;
import edu.hw5.task4.SafePasswordChecker;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void checkCorrectPass() {
        String password = "#Rhv5_71Jc@";
        boolean answer = SafePasswordChecker.check(password);
        assertTrue(answer);
    }

    @Test
    void checkIncorrectPass() {
        String password = "Qwerty123";
        boolean answer = SafePasswordChecker.check(password);
        assertFalse(answer);
    }

    @Test
    void checkEmptyPass() {
        String password = "";
        boolean answer = SafePasswordChecker.check(password);
        assertFalse(answer);
    }
}
