package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "1, I",
        "5, V",
        "10, X",
        "50, L",
        "100, C",
        "500, D",
        "1000, M"
    })
    void testOneCharacterNumbers(int number, String expected) {
        assertThat(Task4.convertToRoman(number)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "2, II",
        "9, IX",
        "16, XVI",
        "19, XIX",
        "41, XLI",
        "49, XLIX",
        "59, LIX",
        "91, XCI",
        "99, XCIX",
        "391, CCCXCI",
        "490, CDXC"
    })
    void testMultipleCharactersNumbers(int number, String expected) {
        assertThat(Task4.convertToRoman(number)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "0,''",
        "-1,''"
    })
    void testWrongInput(int number, String expected) {
        assertThat(Task4.convertToRoman(number)).isEqualTo(expected);
    }
}
