package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.Task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void testCallingInfo() {
        CallingInfo info = Task4.callingInfo();
        assertThat(info.className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(info.methodName()).isEqualTo("testCallingInfo");
    }
}
