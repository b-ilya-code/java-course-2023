package edu.hw5.task8;

public class BinaryRegexMatcher {
    private BinaryRegexMatcher() {}

    public static boolean checkRule1(String string) {
        return string.matches("^([01]{2})*[01]$");
    }

    public static boolean checkRule2(String string) {
        return string.matches("^0([01]{2})*$|^1[01]([01]{2})*$");
    }

    public static boolean checkRule3(String string) {
        return string.matches("^((1*)(0)(1*)(0)(1*)(0)(1*))*$");
    }

    public static boolean checkRule4(String string) {
        return string.matches("^(?!11$|111$)[01]*$");
    }

    public static boolean checkRule5(String string) {
        return string.matches("^1$|^(1[01])*$");
    }

    public static boolean checkRule7(String string) {
        return string.matches("^(?!.*11)[01]*$");
    }
}
