package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Task2 {
    private Task2() {

    }

    public static void cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new NullPointerException();
        }
        String fileName = path.getFileName().toString();

        if (Files.exists(path.resolveSibling(fileName))) {
            String copyFileName = fileName;
            var ext = getExt(fileName);
            int copyNumber = 0;

            while (Files.exists(path.resolveSibling(copyFileName))) {
                copyNumber++;
                copyFileName = fileName + " — копия";
                if (copyNumber == 1) {
                    copyFileName = copyFileName + ext;
                } else {
                    copyFileName = copyFileName + " (" + copyNumber + ")" + ext;
                }
            }
            Path copyPath = path.resolveSibling(copyFileName);
            Files.copy(path, copyPath);
        }
    }

    private static String getExt(String file) {
        if (file.contains(".")) {
            return file.substring(file.lastIndexOf("."));
        }
        return "";
    }
}
