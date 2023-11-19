package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Task4 {
    private final static String OUTPUT = "Programming is learned by writing programs. ― Brian Kernighan";

    private Task4() {

    }

    public static boolean writeText(Path path) {
        try {
            Files.createFile(path);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try (OutputStream fileOutputStream = Files.newOutputStream(path, StandardOpenOption.WRITE);
                 DigestOutputStream checkedOutputStream = new DigestOutputStream(fileOutputStream, messageDigest);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
                 OutputStreamWriter outputStreamWriter =
                     new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
                 PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                printWriter.println(OUTPUT);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            return false;
        }
        return true;
    }
}
