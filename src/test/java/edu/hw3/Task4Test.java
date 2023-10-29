package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void testConvertToRoman() {
        assertThat(Task4.convertToRoman(2)).isEqualTo("II");
        assertThat(Task4.convertToRoman(16)).isEqualTo("XVI");

        assertThat(Task4.convertToRoman(0)).isEqualTo("");
        assertThat(Task4.convertToRoman(-1)).isEqualTo("");
    }
}
