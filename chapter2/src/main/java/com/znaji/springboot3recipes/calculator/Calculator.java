package com.znaji.springboot3recipes.calculator;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Calculator {

    private final List<Operation> operations;

    public Calculator(List<Operation> operations) {
        this.operations = operations;
    }

    public void calculate(int lh, int rh, char op) {

        operations.stream()
                .filter(operation -> operation.support(op))
                .map(operation -> operation.apply(lh, rh))
                .peek(result -> System.out.printf("%d %s %d = %d\n", lh, op, rh, result))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Operation " + op + " not supported"))
                ;

    }

    public int calculate(int a, int b, String op) {
        return operations.stream()
                .filter(o -> o.support(op.charAt(0)))
                .map(o -> o.apply(a, b))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Operation " + op + " not supported"));
    }
}
