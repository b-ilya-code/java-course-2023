package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void checkIfNumIsPalindrome() {
        assertThat(Task5.isPalindromeDescendant(1)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(11)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(123)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(11211230)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(13001120)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(23336014)).isEqualTo(true);
    }
}
