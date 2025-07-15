package com.znaji.springboot3recipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        String clientIp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest().getLocalAddr();
        return String.format("Hello to you from {%s}", clientIp);
    }
}
