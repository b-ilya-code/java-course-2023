package edu.project1;

import edu.project1.guesses.Defeat;
import edu.project1.guesses.FailedGuess;
import edu.project1.guesses.GuessResult;
import edu.project1.guesses.SuccessfulGuess;
import edu.project1.guesses.Win;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final int maxUnsuccessfulAttempts;
    private StringBuilder successfulGuessedLetters;
    private int unsuccessfulAttempts;

    Session(String anAnswer, int aMaxAttempts) {
        answer = anAnswer;
        maxUnsuccessfulAttempts = aMaxAttempts;
        successfulGuessedLetters = new StringBuilder("");
        unsuccessfulAttempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        GuessResult res;

        if (answer.indexOf(guess) != -1) {
            successfulGuessedLetters.append(guess);
            if (getState().equals(answer)) {
                res = new Win(getState(), unsuccessfulAttempts, maxUnsuccessfulAttempts);
            } else {
                res = new SuccessfulGuess(getState(), unsuccessfulAttempts, maxUnsuccessfulAttempts);
            }
        } else {
            ++unsuccessfulAttempts;
            if (unsuccessfulAttempts == maxUnsuccessfulAttempts) {
                res = new Defeat(getState(), unsuccessfulAttempts, maxUnsuccessfulAttempts);
            } else {
                res = new FailedGuess(getState(), unsuccessfulAttempts, maxUnsuccessfulAttempts);
            }
        }

        return res;
    }

    @NotNull GuessResult giveUp() {
        return new Defeat(getState(), unsuccessfulAttempts, maxUnsuccessfulAttempts);
    }

    private String getState() {
        if (successfulGuessedLetters.isEmpty()) {
            return answer.replaceAll(".", "*");
        }

        return answer.replaceAll("[^" + successfulGuessedLetters.toString() + "]", "*");
    }
}
