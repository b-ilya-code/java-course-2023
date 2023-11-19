package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    public void writerCheck() throws IOException {
        Path tempDir = Files.createTempDirectory("Test4");
        Path filePath = tempDir.resolve("task4");
        assertTrue(Task4.writeText(filePath));
    }
}
