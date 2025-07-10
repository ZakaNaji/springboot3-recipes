package com.znaji.springboot3recipes.helloworld;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    @PostConstruct
    private void printHello() {
        System.out.println("Hello From: " + this.getClass().getSimpleName());
    }
}
