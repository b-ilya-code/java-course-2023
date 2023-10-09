package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String brokenStr) {
        if (brokenStr.length() < 2) {
            return brokenStr;
        }

        StringBuilder fixedStr = new StringBuilder();
        char chPrev;
        char ch;

        int evenLength = (brokenStr.length() % 2 == 1) ? brokenStr.length() - 1 : brokenStr.length();

        for (int i = 1; i < evenLength; i += 2) {
            chPrev = brokenStr.charAt(i - 1);
            ch = brokenStr.charAt(i);
            fixedStr.append(ch);
            fixedStr.append(chPrev);
        }

        if (brokenStr.length() % 2 == 1) {
            fixedStr.append(brokenStr.charAt(brokenStr.length() - 1));
        }

        return fixedStr.toString();
    }
}
