package edu.hw3;

import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public class Task4 {
    private Task4() {
    }

    private static final TreeMap<Integer, String> INT_TO_ROMAN_LITERALS = new TreeMap<Integer, String>();

    static {
        INT_TO_ROMAN_LITERALS.put(1000, "M");
        INT_TO_ROMAN_LITERALS.put(900, "CM");
        INT_TO_ROMAN_LITERALS.put(500, "D");
        INT_TO_ROMAN_LITERALS.put(400, "CD");
        INT_TO_ROMAN_LITERALS.put(100, "C");
        INT_TO_ROMAN_LITERALS.put(90, "XC");
        INT_TO_ROMAN_LITERALS.put(50, "L");
        INT_TO_ROMAN_LITERALS.put(40, "XL");
        INT_TO_ROMAN_LITERALS.put(10, "X");
        INT_TO_ROMAN_LITERALS.put(9, "IX");
        INT_TO_ROMAN_LITERALS.put(5, "V");
        INT_TO_ROMAN_LITERALS.put(4, "IV");
        INT_TO_ROMAN_LITERALS.put(1, "I");
    }

    public static final String convertToRoman(int number) {
        if (number <= 0) {
            return new String();
        }

        StringBuilder s = new StringBuilder();

        int copyNumber = number;
        while (copyNumber > 0) {
            int l = INT_TO_ROMAN_LITERALS.floorKey(copyNumber);
            s.append(INT_TO_ROMAN_LITERALS.get(l));
            copyNumber -= l;
        }

        return s.toString();
    }
}
