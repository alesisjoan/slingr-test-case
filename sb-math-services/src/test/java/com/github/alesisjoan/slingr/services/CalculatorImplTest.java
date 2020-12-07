package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.config.BadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CalculatorImplTest {
    
    @Autowired
    Calculator calculator;

    @Test
    void calculate() {
        assertTrue(calculator.calculate("2+2", 0).equals("4"));
        assertTrue(calculator.calculate("3-2", 0).equals("1"));
        assertTrue(calculator.calculate("sqrt(2)", 2).equals("1.4"));
        assertTrue(calculator.calculate("2*2", 0).equals("4"));
        assertTrue(calculator.calculate("100/3", 2).equals("33"));
        assertTrue(calculator.calculate("2.5*2", 0).equals("5"));
        assertTrue(calculator.calculate("((2+3)*2)", 0).equals("10"));
        assertTrue(calculator.calculate("( ( 2 +3 ) * 2)", 0).equals("10"));
    }

    @Test
    void calculateException() {
        BadRequest exception = assertThrows(BadRequest.class, () -> 
                calculator.calculate("2+2qwe", 0));

        BadRequest exception2 = assertThrows(BadRequest.class, () ->
                calculator.calculate("(2+2", 0));
        
        System.out.println(exception.getMessage());
    }
}