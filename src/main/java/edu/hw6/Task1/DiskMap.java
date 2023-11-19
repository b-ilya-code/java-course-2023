package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final static String SEPARATOR = ":";
    private Map<String, String> buffMap;
    private final String filePath;

    public DiskMap(String filePath) throws IOException {
        this.filePath = filePath;
        buffMap = new HashMap<>();
        loadFromFile();
    }

    @Override
    public int size() {
        return buffMap.size();
    }

    @Override
    public boolean isEmpty() {
        return buffMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return buffMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return buffMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return buffMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (buffMap.containsKey(key)) {
            return buffMap.put(key, value);
        }
        try {
            addToFile(key, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        try {
            update(key, buffMap.get(key));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        try (BufferedWriter bf = Files.newBufferedWriter(
            Path.of(filePath),
            StandardOpenOption.TRUNCATE_EXISTING
        )) {
            buffMap.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return buffMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return buffMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return buffMap.entrySet();
    }

    private void addToFile(Object key, Object value) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = key + SEPARATOR + value;
            writer.write(line);
            writer.newLine();
        }
    }

    private void update(Object key, Object value) throws IOException {
        String lineToRemove = key + SEPARATOR + value;
        File tempFile = new File("temp.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true))) {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            File oldFile = new File(filePath);
            oldFile.delete();
            tempFile.renameTo(oldFile);
        }
    }

    private void loadFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int delimiterIndex = line.indexOf(SEPARATOR);
                String key = line.substring(0, delimiterIndex);
                String value = line.substring(delimiterIndex + 1);
                buffMap.put(key, value);
            }
        }
    }
}
