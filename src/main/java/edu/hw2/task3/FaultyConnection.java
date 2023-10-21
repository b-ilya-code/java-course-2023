package edu.hw2.task3;

import java.util.Random;

public class FaultyConnection implements Connection {
    private static final double PROBABILITY_OF_ERROR = 0.5;
    private final Random random;

    public FaultyConnection() {
        this(0L);
    }

    public FaultyConnection(long seed) {
        random = new Random(seed);
    }

    private boolean isError() {
        return random.nextDouble() > PROBABILITY_OF_ERROR;
    }

    @Override
    public void execute(String command) throws ConnectionException {
        if (isError()) {
            throw new ConnectionException("Connection to server lost");
        }
    }

    @Override
    public void close() throws Exception {

    }
}
