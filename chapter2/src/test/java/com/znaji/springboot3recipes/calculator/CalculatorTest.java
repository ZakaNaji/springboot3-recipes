package com.znaji.springboot3recipes.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {

    private Calculator calculator;
    private Operation mockOperation;

    @BeforeEach
    public void init() {
        mockOperation = Mockito.mock(Operation.class);
        calculator = new Calculator(Collections.singletonList(mockOperation));
    }

    @Test
    public void shouldThrowExceptionForUnsupportedOperation() {
        when(mockOperation.support(anyChar())).thenReturn(false);
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(10, 10, '+'));
    }

    @Test
    public void shouldCallApplyWhenCorrectOperationFound() {
        when(mockOperation.support(anyChar())).thenReturn(true);
        when(mockOperation.apply(10, 10)).thenReturn(20);

        calculator.calculate(10, 10, '+');

        verify(mockOperation, times(1)).apply(10,10);
    }


}