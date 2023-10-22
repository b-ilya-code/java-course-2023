package edu.hw2;

import edu.hw2.task3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class Task3Test {
    @Spy
    Random mockRandom = new Random();

    private final static double IS_ERROR = 1;
    private final static double IS_NO_ERROR = 0;

    @Test
    public void checkFaultyConnection() {
        Mockito.doReturn(IS_ERROR).when(mockRandom).nextDouble();

        assertThatThrownBy(() -> {
            new FaultyConnection(mockRandom).execute("command");
        }).isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Connection to server lost");

        Mockito.doReturn(IS_NO_ERROR).when(mockRandom).nextDouble();

        assertThatCode(() -> {
            new FaultyConnection(mockRandom).execute("command");
        }).doesNotThrowAnyException();
    }

    @Test
    public void checkStableConnection() {
        assertThatCode(() -> {
            new StableConnection().execute("command");
        }).doesNotThrowAnyException();
    }

    @Test
    public void checkDefaultConnectionManager() {
        Mockito.doReturn(IS_ERROR).when(mockRandom).nextDouble();

        ConnectionManager failConnectionManager = new DefaultConnectionManager(mockRandom);
        Connection actual = failConnectionManager.getConnection();
        assertThat(actual).isInstanceOf(FaultyConnection.class);

        Mockito.doReturn(IS_NO_ERROR).when(mockRandom).nextDouble();

        ConnectionManager workConnectionManager = new DefaultConnectionManager(mockRandom);
        actual = workConnectionManager.getConnection();
        assertThat(actual).isInstanceOf(StableConnection.class);
    }

    @Test
    public void checkFaultyConnectionManager() {
        ConnectionManager failConnectionManager = new FaultyConnectionManager();
        Connection actual = failConnectionManager.getConnection();
        assertThat(actual).isInstanceOf(FaultyConnection.class);
    }

    @Test
    public void checkPopularCommandExecutor() {
        assertThatThrownBy(() -> {
            PopularCommandExecutor exec = new PopularCommandExecutor(new FaultyConnectionManager(), 2);
            exec.tryExecute("command");
        }).isInstanceOf(ConnectionException.class)
            .hasMessageContaining("The maximum number of attempts to execute a command has been exceeded");

        Mockito.doReturn(IS_NO_ERROR).when(mockRandom).nextDouble();

        assertThatCode(() -> {
            PopularCommandExecutor exec = new PopularCommandExecutor(new DefaultConnectionManager(mockRandom), 2);
            exec.tryExecute("command");
        }).doesNotThrowAnyException();
    }
}
