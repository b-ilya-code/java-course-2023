package edu.hw5;

import org.junit.jupiter.api.Test;
import edu.hw5.task1.TimeCounter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void checkCalculateCorrectValue() {
        String[] time = {"2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"};
        String result = TimeCounter.getTimeDifference(time);
        assertThat(result).isEqualTo("3ч 40м");
    }

    @Test
    void checkCalculateIncorrectValue() {
        String[] time = {"2022-03-12, 20:20 - 2022-03-12, 23:50",
            "wrong str",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"};
        String result = TimeCounter.getTimeDifference(time);
        assertThat(result).isEqualTo("3ч 40м");
    }

    @Test
    void checkCalculateFullyIncorrectValue() {
        String[] time = {"2022-03-12,20:20 - 2022-03-12, 23:50",
            "Некорректная строка появилась из ниоткуда",
            "2022-04-01 21:30 - 2022-04-02 01:20"};
        String result = TimeCounter.getTimeDifference(time);
        System.out.println(result);
        assertThat(result).isEqualTo("0ч 0м");
    }

    @Test
    void checkCalculateReverseValue() {
        String[] time = {"2022-03-12, 23:50 - 2022-03-12, 20:20",
            "2022-04-02, 01:20 - 2022-04-01, 21:30"};
        String result = TimeCounter.getTimeDifference(time);
        assertThat(result).isEqualTo("3ч 40м");
    }
}
