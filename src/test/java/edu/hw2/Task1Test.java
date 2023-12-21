package edu.hw2;
import edu.hw2.task1.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void testEvaluateExpr() {
        assertThat(new Constant(1).evaluate()).isEqualTo(1, withPrecision(1e-6));
        assertThat(new Negate(new Constant(1)).evaluate()).isEqualTo(-1, withPrecision(1e-6));

        var zero = new Constant(0);
        var one = new Constant(1);
        var two = new Constant(2);
        var negOne = new Constant(-1);

        assertThat(new Exp(two,0).evaluate()).isEqualTo(1, withPrecision(1e-6));
        assertThat(new Exp(two,-1).evaluate()).isEqualTo(0.5, withPrecision(1e-6));

        assertThat(new Addition(one, two).evaluate()).isEqualTo(3, withPrecision(1e-6));
        assertThat(new Addition(one, zero).evaluate()).isEqualTo(1, withPrecision(1e-6));
        assertThat(new Addition(one, negOne).evaluate()).isEqualTo(0, withPrecision(1e-6));

        assertThat(new Multiplication(one, two).evaluate()).isEqualTo(2, withPrecision(1e-6));
        assertThat(new Multiplication(one, zero).evaluate()).isEqualTo(0, withPrecision(1e-6));
        assertThat(new Multiplication(negOne, negOne).evaluate()).isEqualTo(1, withPrecision(1e-6));
    }
}
