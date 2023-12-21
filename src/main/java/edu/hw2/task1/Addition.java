package edu.hw2.task1;

public record Addition(Expr lhs, Expr rhs) implements Expr {
    @Override
    public double evaluate() {
        return lhs.evaluate() + rhs.evaluate();
    }
}
