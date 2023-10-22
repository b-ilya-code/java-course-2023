package edu.project1;

import edu.project1.guesses.Defeat;
import edu.project1.guesses.FailedGuess;
import edu.project1.guesses.GuessResult;
import edu.project1.guesses.SuccessfulGuess;
import edu.project1.guesses.Win;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {
    @Test
    void win() {
        Session session = new Session("x", 1);
        GuessResult result = session.guess('x');

        assertThat(result).isInstanceOf(Win.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(1);
        assertThat(result.state()).isEqualTo("x");
    }

    @Test
    void defeat() {
        Session session = new Session("x", 1);
        GuessResult result = session.guess('y');

        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.attempt()).isEqualTo(1);
        assertThat(result.maxAttempts()).isEqualTo(1);
        assertThat(result.state()).isEqualTo("*");
    }

    @Test
    void successful() {
        Session session = new Session("hello", 2);
        GuessResult result = session.guess('h');

        assertThat(result).isInstanceOf(SuccessfulGuess.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
        assertThat(result.state()).isEqualTo("h****");
    }

    @Test
    void repeated() {
        Session session = new Session("hello", 2);
        session.guess('h');
        GuessResult result = session.guess('h');

        assertThat(result).isInstanceOf(SuccessfulGuess.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
        assertThat(result.state()).isEqualTo("h****");
    }

    @Test
    void failed() {
        Session session = new Session("hello", 2);
        GuessResult result = session.guess('a');

        assertThat(result).isInstanceOf(FailedGuess.class);
        assertThat(result.attempt()).isEqualTo(1);
        assertThat(result.maxAttempts()).isEqualTo(2);
        assertThat(result.state()).isEqualTo("*****");
    }

    @Test
    void giveUp() {
        Session session = new Session("hello", 2);
        GuessResult result = session.giveUp();

        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
        assertThat(result.state()).isEqualTo("*****");
    }
}
