package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    void checkStepsToKaprekarConstant() {
        //valid input
        assertThat(Task6.countK(6621)).isEqualTo(5);
        assertThat(Task6.countK(6554)).isEqualTo(4);
        assertThat(Task6.countK(1234)).isEqualTo(3);
        assertThat(Task6.countK(3524)).isEqualTo(3);

        //invalid input
        assertThat(Task6.countK(123)).isEqualTo(-1);
        assertThat(Task6.countK(12345)).isEqualTo(-1);
        assertThat(Task6.countK(1111)).isEqualTo(-1);
    }
}
