package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String sourceStr) {
        StringBuilder encryptedStr = new StringBuilder();
        char c;
        for (int i = 0; i < sourceStr.length(); ++i) {
            c = sourceStr.charAt(i);
            if (c >= 'a' && c <= 'z') {
                encryptedStr.append((char) ('z' - (c - 'a')));
            } else if (c >= 'A' && c <= 'Z') {
                encryptedStr.append((char) ('Z' - (c - 'A')));
            } else {
                encryptedStr.append(c);
            }
        }

        return encryptedStr.toString();
    }
}
