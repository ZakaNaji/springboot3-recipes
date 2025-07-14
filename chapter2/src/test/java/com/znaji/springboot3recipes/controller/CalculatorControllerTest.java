package com.znaji.springboot3recipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.znaji.springboot3recipes.calculator.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Calculator calculator;

    @Test
    void successfulCalculation() throws Exception {

        when(calculator.calculate(25, 25, "+")).thenReturn(50);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new CalculatedDto(25, 25, "+"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("50"));

        verify(calculator, times(1)).calculate(25, 25, "+");
    }

}