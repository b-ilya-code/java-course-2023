package edu.hw4.errors;

public class ValidationError extends Throwable {
    public ValidationError(String message) {
        super(message);
    }
}
