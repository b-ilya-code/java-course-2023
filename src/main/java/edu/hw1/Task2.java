package edu.hw1;

public class Task2 {
    private Task2() {
    }

    private static final int RADIX = 10;

    public static int countDigits(int num) {
        if (num < 0) {
            return -1;
        }
        if (num == 0) {
            return 0;
        }

        int digits = 0;
        int numCopy = num;
        for (; numCopy > 0; numCopy /= RADIX, digits++) {}

        return digits;
    }
}
