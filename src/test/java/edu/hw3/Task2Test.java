package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void testClusterize() {
        assertThat(Task2.clusterize("()()()")).isEqualTo(new ArrayList<String>(List.of("()", "()", "()")));
        assertThat(Task2.clusterize("((()))")).isEqualTo(new ArrayList<String>(List.of("((()))")));
        assertThat(Task2.clusterize("((()))(())()()(()())")).isEqualTo(new ArrayList<String>(List.of("((()))", "(())", "()", "()", "(()())")));
        assertThat(Task2.clusterize("((())())(()(()()))")).isEqualTo(new ArrayList<String>(List.of("((())())", "(()(()()))")));

        assertThat(Task2.clusterize("")).isEqualTo(new ArrayList<String>());
        assertThat(Task2.clusterize("1()")).isEqualTo(new ArrayList<String>());
        assertThat(Task2.clusterize("()))((()")).isEqualTo(new ArrayList<String>());
        assertThat(Task2.clusterize("(abc)")).isEqualTo(new ArrayList<String>(List.of("(abc)")));
    }
}
