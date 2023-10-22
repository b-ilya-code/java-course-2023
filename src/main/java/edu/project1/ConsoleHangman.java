package edu.project1;

import edu.project1.guesses.Defeat;
import edu.project1.guesses.GuessResult;
import edu.project1.guesses.Win;
import java.util.Scanner;

class ConsoleHangman {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static int MAX_ATTEMPTS_BY_DEFAULT = 5;
    private final int maxAttempts;
    private final Dictionary dict;

    ConsoleHangman(Dictionary aDict) {
        this(aDict, MAX_ATTEMPTS_BY_DEFAULT);
    }

    ConsoleHangman(Dictionary aDict, int aMaxAttempts) {
        dict = aDict;
        maxAttempts = aMaxAttempts;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void run() {
        Session session = new Session(dict.randomWord(), maxAttempts);

        while (true) {
            System.out.println("Guess a letter:");

            if (!SCANNER.hasNextLine()) {
                printMessage(session.giveUp());
                break;
            }

            String input = SCANNER.nextLine();

            if (isValid(input)) {
                GuessResult guessResult = tryGuess(session, input.charAt(0));
                printMessage(guessResult);

                if (guessResult instanceof Win || guessResult instanceof Defeat) {
                    break;
                }
            }
        }
    }

    private static boolean isValid(String input) {
        return input.length() == 1;
    }

    private GuessResult tryGuess(Session session, char letter) {
        return session.guess(letter);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printMessage(GuessResult guessResult) {
        System.out.println(guessResult.message());
    }
}
