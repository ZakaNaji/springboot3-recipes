package com.znaji.springboot3recipes.calculator;

public class Multiplication implements Operation {
    @Override
    public boolean support(char op) {
        return op == 'x';
    }

    @Override
    public int apply(int lh, int rh) {
        return lh * rh;
    }
}
