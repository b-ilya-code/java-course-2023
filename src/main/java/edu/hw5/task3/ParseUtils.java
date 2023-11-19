package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ParseUtils {
    private ParseUtils() {}

    public static Optional<LocalDate> parseDate(String string1) {
        LocalDate localDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(string1, formatter);
        } catch (IllegalArgumentException | DateTimeParseException ignored) {

        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
            localDate = LocalDate.parse(string1, formatter);
        } catch (IllegalArgumentException | DateTimeParseException ignored) {
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            localDate = LocalDate.parse(string1, formatter);
        } catch (IllegalArgumentException | DateTimeParseException ignored) {
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            localDate = LocalDate.parse(string1, formatter);
        } catch (IllegalArgumentException | DateTimeParseException ignored) {
        }

        if ("tomorrow".equalsIgnoreCase(string1)) {
            localDate = LocalDate.now().plusDays(1);
        } else if ("today".equalsIgnoreCase(string1)) {
            localDate = LocalDate.now();
        } else if ("yesterday".equalsIgnoreCase(string1)) {
            localDate = LocalDate.now().minusDays(1);
        } else if (string1.endsWith(" day ago")) {
            try {
                int daysAgo = Integer.parseInt(string1.split(" ")[0]);
                localDate = LocalDate.now().minusDays(daysAgo);
            } catch (NumberFormatException ignored) {
            }
        } else if (string1.endsWith(" days ago")) {
            try {
                int daysAgo = Integer.parseInt(string1.split(" ")[0]);
                localDate = LocalDate.now().minusDays(daysAgo);
            } catch (NumberFormatException ignored) {
            }
        }
        if (localDate != null) {
            return Optional.of(localDate);
        }
        return Optional.empty();

    }
}
