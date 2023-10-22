package edu.project1.guesses;

import org.jetbrains.annotations.NotNull;

public record Win(String state, int attempt, int maxAttempts) implements GuessResult {
    @NotNull public String message() {
        return "\nThe word: " + state() + "\n\nYou won!";
    }
}
