package com.znaji.springboot3recipes.controller;

import com.znaji.springboot3recipes.calculator.Calculator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public int calculate(@RequestBody CalculatedDto dto) {
        return calculator.calculate(dto.lh(), dto.rh(), dto.op());
    }
}
