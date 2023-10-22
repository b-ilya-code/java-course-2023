package edu.project1.guesses;

import org.jetbrains.annotations.NotNull;

sealed public interface GuessResult permits Defeat, Win, SuccessfulGuess, FailedGuess {
    String state();

    int attempt();

    int maxAttempts();

    @NotNull String message();
}
