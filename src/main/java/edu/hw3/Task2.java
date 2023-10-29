package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {
    }

    public static ArrayList<String> clusterize(String s) {
        ArrayList<String> clusters = new ArrayList<>();

        if (!isCorrect(s)) {
            return new ArrayList<>();
        }

        int numOfUnclosedParentheses = 1;
        int posClusterStart = 0;
        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                ++numOfUnclosedParentheses;
            } else if (c == ')') {
                --numOfUnclosedParentheses;
            }

            if (numOfUnclosedParentheses < 0) {
                return new ArrayList<>();
            } else if (numOfUnclosedParentheses == 0) {
                clusters.add(s.substring(posClusterStart, i + 1));
                posClusterStart = i + 1;
            }
        }

        return clusters;
    }

    private static boolean isCorrect(String s) {
        return !s.isEmpty() && s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')';
    }
}
