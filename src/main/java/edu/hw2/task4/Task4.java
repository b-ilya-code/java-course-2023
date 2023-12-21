package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] methods = new Throwable().getStackTrace();
        return new CallingInfo(methods[1].getClassName(), methods[1].getMethodName());
    }
}
