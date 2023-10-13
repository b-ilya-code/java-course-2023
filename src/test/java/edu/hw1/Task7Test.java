package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void checkCyclicBitShift() {
        assertThat(Task7.rotateRight(8, 1)).isEqualTo(4);
        assertThat(Task7.rotateLeft(16, 1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(17, 2)).isEqualTo(6);
        assertThat(Task7.rotateLeft(1, -1)).isEqualTo(Task7.rotateRight(1, 1));
        assertThat(Task7.rotateRight(1, -1)).isEqualTo(Task7.rotateLeft(1, 1));
    }
}
