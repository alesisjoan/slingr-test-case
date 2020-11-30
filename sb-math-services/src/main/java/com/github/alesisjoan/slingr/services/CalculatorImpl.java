package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.config.BadRequest;
import com.github.alesisjoan.slingr.controllers.CalculatorController;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorImpl implements Calculator{

    private static final Logger log = LoggerFactory
            .getLogger(CalculatorImpl.class);

    @Override
    public String calculate(String expression, Integer digits) {
        log.info(String.format("To evaluate expression {} with {} digits", expression, digits));
        
        Expression expression1 = null; 
        try{
            expression1 = new ExpressionBuilder(expression).build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BadRequest("Error while creating expression, please check the expression");
        }
        double result = expression1.evaluate();
       
        String evaluated = setSignificanDigits(result, digits);
        log.debug("Result: "+evaluated);
        return evaluated;
    }
    
    private static String setSignificanDigits(double value, int significantDigits) {
        if (significantDigits < 0) throw new IllegalArgumentException();

        // this is more precise than simply doing "new BigDecimal(value);"
        BigDecimal bd = new BigDecimal(value, MathContext.DECIMAL64);
        bd = bd.round(new MathContext(significantDigits, RoundingMode.HALF_UP));
        final int precision = bd.precision();
        if (precision < significantDigits)
            bd = bd.setScale(bd.scale() + (significantDigits-precision));
        return bd.toPlainString();
    }
}
