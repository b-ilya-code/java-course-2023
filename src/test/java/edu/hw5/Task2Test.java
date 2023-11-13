package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import edu.hw5.task2.FridayThirteenthCounter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void checkCorrectWorkFindFridays() {
        int year = 1925;
        List<LocalDate> answer = new ArrayList<>();
        answer.add(LocalDate.parse("1925-02-13"));
        answer.add(LocalDate.parse("1925-03-13"));
        answer.add(LocalDate.parse("1925-11-13"));
        List<LocalDate> result = FridayThirteenthCounter.findFridaysOnThe13th(year);
        assertThat(result).isEqualTo(answer);
    }

    @Test
    void checkCorrectWorkNextFriday() {
        LocalDate date = LocalDate.parse("1925-02-13");
        LocalDate answer = LocalDate.parse("1925-03-13");
        LocalDate result = FridayThirteenthCounter.findNextFridayThe13(date);
        assertThat(result).isEqualTo(answer);
    }
}
