package edu.hw3.Task7;

import java.util.Comparator;

public class NullComparator<T> implements Comparator<T> {
    @Override
    public int compare(T lhs, T rhs) {
        if (lhs == null && rhs == null) {
            return 0;
        } else if (lhs == null) {
            return -1;
        } else if (rhs == null) {
            return 1;
        } else {
            return ((Comparable<T>) lhs).compareTo(rhs);
        }
    }
}
