package edu.hw2.task2;

public class Rectangle {
    final protected int width;
    final protected int height;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(int aWidth, int aHeight) {
        width = aWidth;
        height = aHeight;
    }

    public Rectangle setWidth(int aWidth) {
        return new Rectangle(aWidth, height);
    }

    public Rectangle setHeight(int aHeight) {
        return new Rectangle(width, aHeight);
    }

    public double area() {
        return width * height;
    }
}
