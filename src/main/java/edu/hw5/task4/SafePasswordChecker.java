package edu.hw5.task4;

import java.util.regex.Pattern;

public class SafePasswordChecker {
    private SafePasswordChecker() {
    }

    public static boolean check(String password) {
        Pattern pattern = Pattern.compile("[~!@$%^&|*]+");
        return pattern.matcher(password).find();
    }
}
