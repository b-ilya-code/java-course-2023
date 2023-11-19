package edu.hw6;

import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipInputStream;

import static edu.hw6.Task3.AbstractFilter.READABLE;
import static edu.hw6.Task3.AbstractFilter.REGULAR_FILE;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;

import static edu.hw6.Task3.AbstractFilter.regexContains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    public void filterTest() throws IOException {
        Path tempDir = Files.createTempDirectory("Test3");
        Path filePath = tempDir.resolve("Task-3.png");
        var file = Files.createFile(filePath);
        try {
            Files.write(filePath, List.of("Test file for task 3"), StandardOpenOption.CREATE);

            Files.setAttribute(filePath, "dos:readonly", true);

        } catch (IOException e) {
        }

        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(largerThan(20))
            .and(magicNumber(84))
            .and(globMatches("*.png"))
            .and(regexContains("-"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)) {
            int i = 0;
            for (var a : entries) {
                assertEquals(a.getFileName().toString(), "Task-3.png");
                ++i;
            }
            assertEquals(i, 1);
        }
    }
}
