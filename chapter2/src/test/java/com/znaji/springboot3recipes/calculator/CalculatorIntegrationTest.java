package com.znaji.springboot3recipes.calculator;

import com.znaji.springboot3recipes.Chapter2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Chapter2.class)
@ExtendWith(OutputCaptureExtension.class)
class CalculatorIntegrationTest {
    @Autowired
    Calculator calculator;

    @MockitoBean(name = "multiplication")
    Operation multiplication;

    @Test
    void divisionOpShouldFail() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(10, 10, '/'));
    }

    @Test
    void subOpShouldSucceed(CapturedOutput output) {
        calculator.calculate(10, 10, '-');
        assertTrue(output.getOut().contains(String.format("%d %s %d = %d", 10, '-', 10, 0)));
    }

    @Test
    void calculatorShouldHave3Operation() {
        Collection<Operation> operations = (Collection<Operation>) ReflectionTestUtils.getField(calculator, "operations");
        assertEquals(3, operations.size());
    }

    @Test
    void mockMultiplication(CapturedOutput output) {
        when(multiplication.support('*')).thenReturn(true);
        when(multiplication.apply(10, 10)).thenReturn(100);

        calculator.calculate(10, 10, '*');
        assertTrue(output.getOut().contains(String.format("%d %s %d = %d", 10, '*', 10, 100)));
    }
}
