package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void countDigitsInNum() {
        //valid input
        assertThat(Task2.countDigits(0)).isEqualTo(0);
        assertThat(Task2.countDigits(123)).isEqualTo(3);

        //invalid input
        assertThat(Task2.countDigits(-123)).isEqualTo(-1);
    }
}
