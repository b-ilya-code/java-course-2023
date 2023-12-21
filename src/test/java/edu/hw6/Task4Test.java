package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    public void writerCheck() throws IOException {
        Path tempDir = Files.createTempDirectory("Test4");
        Path filePath = tempDir.resolve("task4");
        assertTrue(Task4.writeText(filePath));

        File file = new File(filePath.toString());
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            String result = bufferedReader.readLine();

            assertThat(Task4.OUTPUT).isEqualTo(result);
        } catch (IOException e) {
        }
    }
}
