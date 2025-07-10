package com.znaji.springboot3recipes.config;

import com.znaji.springboot3recipes.calculator.Calculator;
import com.znaji.springboot3recipes.calculator.Operation;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Operation sum() {
        return new Operation() {
            @Override
            public boolean support(char op) {
                return op == '+';
            }

            @Override
            public int cal(int lh, int rh) {
                return lh + rh;
            }
        };
    }

    @Bean
    public Operation sub() {
        return new Operation() {
            @Override
            public boolean support(char op) {
                return op == '-';
            }

            @Override
            public int cal(int lh, int rh) {
                return lh - rh;
            }
        };
    }

    @Bean
    public ApplicationRunner runner (Calculator calculator) {
        return args -> {
            System.out.println("Inside Runner doing somme cal tests: ");
            calculator.calculate(10, 20, '+');
            calculator.calculate(10, 20, '-');
        };
    }
}
