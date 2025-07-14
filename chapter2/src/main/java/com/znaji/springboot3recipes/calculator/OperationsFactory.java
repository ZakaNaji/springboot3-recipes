package com.znaji.springboot3recipes.calculator;

public class OperationsFactory {

    public Operation createMulOp() {
        return new Operation() {
            @Override
            public boolean support(char op) {
                return '*' == op;
            }

            @Override
            public int apply(int lh, int rh) {
                return lh * rh;
            }
        };
    }
}
