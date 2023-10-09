package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int num) {
        if (num >= 0) {
            String strNum = String.valueOf(num);
            if (checkIfStrIsPalindrome(strNum)) {
                return true;
            }

            if (strNum.length() % 2 == 1) {
                return false;
            }

            while (strNum.length() > 1) {
                strNum = getDescendantOfStrNum(strNum);
                if (checkIfStrIsPalindrome(strNum)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static String getDescendantOfStrNum(String strNum) {
        StringBuilder descendantOfStrNum = new StringBuilder();

        for (int i = 1; i < strNum.length(); i += 2) {
            descendantOfStrNum.append(String.valueOf(strNum.charAt(i - 1) - '0' + strNum.charAt(i) - '0'));
        }

        return descendantOfStrNum.toString();
    }

    private static boolean checkIfStrIsPalindrome(String str) {
        StringBuilder reverseStr = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            reverseStr.append(str.charAt(i));
        }

        return str.contentEquals(reverseStr);
    }
}
