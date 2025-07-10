package com.znaji.springboot3recipes.calculator;


public interface Operation {
    boolean support(char op);

    int cal(int lh, int rh);
}
