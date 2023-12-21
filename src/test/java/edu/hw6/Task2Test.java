package edu.hw6;

import edu.hw6.Task2.Task2;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    public void cloneFileWithoutExtensionTest() throws IOException {
        Path tempDir = Files.createTempDirectory("Test2");
        Path filePath = tempDir.resolve("clone");
        var file = Files.createFile(filePath);
        FileWriter fileWriter = new FileWriter(file.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Test");
        Task2.cloneFile(filePath);
        Task2.cloneFile(filePath);
        Task2.cloneFile(filePath);
        assertTrue(Files.exists(Path.of(filePath + " — копия")));
        assertTrue(Files.exists(Path.of(filePath + " — копия (2)")));
        assertTrue(Files.exists(Path.of(filePath + " — копия (3)")));
    }

    @Test
    public void cloneFileWithExtensionTest() throws IOException {
        Path tempDir = Files.createTempDirectory("Test2");
        Path filePath = tempDir.resolve("clone.txt");
        var file = Files.createFile(filePath);
        FileWriter fileWriter = new FileWriter(file.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Test");
        Task2.cloneFile(filePath);
        Task2.cloneFile(filePath);
        Task2.cloneFile(filePath);
        assertTrue(Files.exists(Path.of(filePath + " — копия.txt")));
        assertTrue(Files.exists(Path.of(filePath + " — копия (2).txt")));
        assertTrue(Files.exists(Path.of(filePath + " — копия (3).txt")));
    }
}
