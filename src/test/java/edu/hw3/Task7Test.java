package edu.hw3;

import edu.hw3.Task7.NullComparator;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void correctnessOfNullComparator() {
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator<String>());

        treeMap.put(null, "test");

        assertThat(treeMap.containsKey(null)).isTrue();
    }
}
