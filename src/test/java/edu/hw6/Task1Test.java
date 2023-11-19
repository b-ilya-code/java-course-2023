package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    public void putMapTest() throws IOException {
        Path tempDir = Files.createTempDirectory("diskmap_test");
        Path filePath = tempDir.resolve("DiskMapTest");
        var file = Files.createFile(filePath);
        Map<String, String> map = new DiskMap(filePath.toString());
        map.put("hello", "world");
        map.put("hello2", "world2");
        map.put("hello3", "world3");
        Map<String, String> map2 = new DiskMap(filePath.toString());
        assertEquals(map2.get("hello"), "world");
        assertEquals(map2.get("hello2"), "world2");
        assertEquals(map2.get("hello3"), "world3");
    }

    @Test
    public void removeTest() throws IOException {
        Path tempDir = Files.createTempDirectory("diskmap_test");
        Path filePath = tempDir.resolve("DiskMapTest");
        var file = Files.createFile(filePath);
        Map<String, String> map = new DiskMap(filePath.toString());
        map.put("hello", "world");
        map.put("hello2", "world2");
        map.put("hello3", "world3");
        map.remove("hello");
        Map<String, String> map2 =  new DiskMap(filePath.toString());
        assertEquals(map2.keySet(), Set.of("hello2", "hello3"));
    }
}
