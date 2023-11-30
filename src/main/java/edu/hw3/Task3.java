package edu.hw3;

import java.util.HashMap;

public class Task3 {
    private Task3() {
    }

    public static <T> HashMap<T, Integer> freqDict(T[] objects) {
        HashMap<T, Integer> aFreqDict = new HashMap<>();
        for (T obj : objects) {
            aFreqDict.merge(obj, 1, Integer::sum);
        }

        return aFreqDict;
    }
}
