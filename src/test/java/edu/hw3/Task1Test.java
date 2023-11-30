package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void testAtbashCipher() {
        assertThat(Task1.atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(Task1.atbash("AZ")).isEqualTo("ZA");
        assertThat(Task1.atbash("")).isEqualTo("");
        assertThat(Task1.atbash("123")).isEqualTo("123");
        assertThat(Task1.atbash("Привет мир!")).isEqualTo("Привет мир!");
    }
}
