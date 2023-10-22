package edu.project1.guesses;

import org.jetbrains.annotations.NotNull;

public record SuccessfulGuess(String state, int attempt, int maxAttempts) implements GuessResult {
    @NotNull public String message() {
        return "Hit!\n\nThe word: " + state() + "\n";
    }
}
