package edu.project1.guesses;

import org.jetbrains.annotations.NotNull;

public record FailedGuess(String state, int attempt, int maxAttempts) implements GuessResult {
    @NotNull public String message() {
        return "Missed, mistake " + String.valueOf(attempt()) + " out of " + maxAttempts() + ". "
            + "\n\nThe word: " + state() + "\n";
    }
}
