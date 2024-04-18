package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager aManager, int aMaxAttempts) {
        manager = aManager;
        maxAttempts = aMaxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws ConnectionException {
        for (int i = 0; i < maxAttempts - 1; ++i) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception ignored) {
            }
        }

        try (Connection connection = manager.getConnection()) {
            connection.execute(command);
        } catch (Exception e) {
            throw new ConnectionException("The maximum number of attempts to execute a command has been exceeded", e);
        }
    }
}
