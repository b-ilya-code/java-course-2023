package edu.hw1;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        int[] minMax1 = findMinMax(a1);
        int[] minMax2 = findMinMax(a2);

        return (minMax1[0] > minMax2[0]) && (minMax1[1] < minMax2[1]);
    }

    private static int[] findMinMax(int[] a) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > max) {
                max = a[i];
            }
        }

        return new int[] {min, max};
    }
}
