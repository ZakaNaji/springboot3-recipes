package com.znaji.springboot3recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
       try (var ctx = SpringApplication.run(App.class, args)) {
           System.out.println("beans count: " + ctx.getBeanDefinitionCount());

           var names = ctx.getBeanDefinitionNames();
           Arrays.sort(names);
           Arrays.asList(names).forEach(System.out::println);
       }
    }
}
