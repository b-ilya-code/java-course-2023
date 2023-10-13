package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (shift < 0) {
            return rotateRight(n, -shift);
        }

        final int BIT_DEPTH = getBitDepth(n);
        return ~(-1 << BIT_DEPTH) & ((n << shift) | (n >>> (BIT_DEPTH - shift)));
    }

    public static int rotateRight(int n, int shift) {
        if (shift < 0) {
            return rotateLeft(n, -shift);
        }

        final int BIT_DEPTH = getBitDepth(n);
        return ~(-1 << BIT_DEPTH) & ((n >>> shift) | (n << (BIT_DEPTH - shift)));
    }

    private static int getBitDepth(int n) {
        return (int) (Math.log(n) / Math.log(2)) + 1;
    }
}
