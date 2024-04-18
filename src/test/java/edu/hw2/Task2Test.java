package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        Rectangle rect2 = rect.setWidth(20).setHeight(10);

        assertThat(rect2.area()).isEqualTo(200.0);
    }

    @Test
    void checkSquare() {
        Square square = new Square(10); // square with sides 10 and 10
        assertThat(square.area()).isEqualTo(100.0);

        Rectangle rect = square.setWidth(20); // rectangle with sides 20 and 10
        assertThat(rect.area()).isEqualTo(200.0);

        Square square2 = square.setSide(20); // square with sides 20 and 20
        assertThat(square2.area()).isEqualTo(400.0);
    }
}
