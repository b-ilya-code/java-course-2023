package edu.hw1;

public class Task7 {
    private Task7() {
    }

    private static final int BIT_DEPTH_INT = 32;

    public static int rotateLeft(int n, int shift) {
        return (n << shift) | (n >>> (BIT_DEPTH_INT - shift));
    }

    public static int rotateRight(int n, int shift) {
        return (n >>> shift) | (n << (BIT_DEPTH_INT - shift));
    }
}
