package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void checkArrayNesting() {
        assertThat(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 0, 6 })).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] { 3, 1 }, new int[] { 4, 0 })).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] { 9, 9, 8 }, new int[] { 8, 9 })).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 2, 3 })).isEqualTo(false);
    }
}
