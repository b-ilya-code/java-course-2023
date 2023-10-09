package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    private final static int KAPREKAR_CONSTANT = 6174;
    private final static int CORRECT_NUM_LENGTH = 4;

    public static int countK(int num) {
        return countK(num, 0);
    }

    private static int countK(int num, int stepsToKaprekarConstant) {
        if (num == KAPREKAR_CONSTANT) {
            return stepsToKaprekarConstant;
        }

        String ascStr = sortString(String.valueOf(num));
        if (ascStr.length() != CORRECT_NUM_LENGTH || allCharactersSame(ascStr)) {
            return -1;
        }

        String descStr = new StringBuilder(ascStr).reverse().toString();

        ascStr = padStringWithZeros(ascStr, CORRECT_NUM_LENGTH);
        descStr = padStringWithZeros(descStr, CORRECT_NUM_LENGTH);

        int asc = Integer.parseInt(ascStr);
        int desc = Integer.parseInt(descStr);

        return countK(Math.max(asc, desc) - Math.min(asc, desc)) + 1;
    }

    private static boolean allCharactersSame(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(0)) {
                return false;
            }
        }

        return true;
    }

    public static String padStringWithZeros(String s, int length) {
        return String.format("%1$" + length + "s", s).replace(' ', '0');
    }

    private static String sortString(String inputString) {
        // Converting input string to character array
        char[] tempArray = inputString.toCharArray();

        // Sorting temp array using
        Arrays.sort(tempArray);

        // Returning new sorted string
        return new String(tempArray);
    }
}
