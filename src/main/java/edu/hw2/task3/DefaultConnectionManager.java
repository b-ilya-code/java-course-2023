package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double PROBABILITY_OF_ERROR = 0.5;
    private final Random random;

    public DefaultConnectionManager() {
        this(new Random());
    }

    public DefaultConnectionManager(Random aRandom) {
        random = aRandom;
    }

    private boolean isError() {
        return random.nextDouble() > PROBABILITY_OF_ERROR;
    }

    @Override
    public Connection getConnection() {
        if (isError()) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
