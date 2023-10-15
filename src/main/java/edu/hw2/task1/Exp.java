package edu.hw2.task1;

public record Exp(Expr expr, double exp) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), exp);
    }
}
