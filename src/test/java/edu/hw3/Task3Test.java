package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void testFreqDict() {
        assertThat(Task3.freqDict(new String[] {"a", "bb", "a", "bb"}))
            .isEqualTo(new HashMap<String, Integer>(Map.of("bb",2,"a",2)));

        assertThat(Task3.freqDict(new Integer[] {1, 1, 2, 2}))
            .isEqualTo(new HashMap<Integer, Integer>(Map.of(1,2,2,2)));
    }
}
