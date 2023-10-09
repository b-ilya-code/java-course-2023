package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void countSecondsInTimeStr() {
        //valid input
        assertThat(Task1.minutesToSeconds("01:30")).isEqualTo(90);
        assertThat(Task1.minutesToSeconds("100:30")).isEqualTo(6030);
        assertThat(Task1.minutesToSeconds("00:00")).isEqualTo(0);
        assertThat(Task1.minutesToSeconds("000:00")).isEqualTo(0);

        //invalid input
        assertThat(Task1.minutesToSeconds("0:0")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("00:0")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("0:00")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("-01:00")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("00:-1")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("00:60")).isEqualTo(-1);
    }
}
