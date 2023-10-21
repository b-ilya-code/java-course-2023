package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.FaultyConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class Task3Test {
    @Spy
    FaultyConnection mockFaultyConnection = new FaultyConnection();

    @Spy
    Random mockRandom = new Random();

    private final static double IS_ERROR = 1;
    private final static double IS_NO_ERROR = 0;

    @Test
    public void checkFaultyConnection() {
        Mockito.doReturn(true).when(mockFaultyConnection).isError();

        assertThatThrownBy(() -> {
            mockFaultyConnection.execute("command");
        }).isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Connection to server lost");
    }
}
