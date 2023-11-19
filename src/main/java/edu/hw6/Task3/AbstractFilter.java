package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;

    default AbstractFilter and(AbstractFilter other) {
        return path -> accept(path) && other.accept(path);
    }

    static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... bytes) {
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

    static AbstractFilter globMatches(String glob) {
        return path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return matcher.matches(path.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return path -> {
            String fileName = path.getFileName().toString();
            return fileName.matches(".*" + regex + ".*");
        };
    }
}
