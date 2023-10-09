package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void checkCyclicBitShift() {
        //valid input
        assertThat(Task7.rotateRight(1, 1)).isEqualTo(-2147483648);
        assertThat(Task7.rotateLeft(1, 31)).isEqualTo(-2147483648);
        assertThat(Task7.rotateLeft(1, 3)).isEqualTo(8);

        //invalid input, but processed correctly
        assertThat(Task7.rotateLeft(-2147483648, 1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(1, -1)).isEqualTo(Task7.rotateRight(1, 1));
        assertThat(Task7.rotateRight(1, -1)).isEqualTo(Task7.rotateLeft(1, 1));
    }
}
