package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;

public class DirectoryStreamFilterCollection {
    private DirectoryStreamFilterCollection() {
    }

    public static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter magicNumber(int... bytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                for (int i = 0; i < bytes.length; i++) {
                    if (fileBytes[i] != (byte) bytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return matcher.matches(path.getFileName());
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> {
            String fileName = path.getFileName().toString();
            return fileName.matches(".*" + regex + ".*");
        };
    }
}
