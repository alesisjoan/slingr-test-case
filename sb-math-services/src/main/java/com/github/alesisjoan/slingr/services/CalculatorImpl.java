package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.config.BadRequest;
import com.github.alesisjoan.slingr.controllers.CalculatorController;
import com.github.alesisjoan.slingr.domain.MathExpression;
import com.github.alesisjoan.slingr.domain.MathResult;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorImpl implements Calculator{

    private static final Logger log = LoggerFactory
            .getLogger(CalculatorImpl.class);
    
    @Autowired
    CacheService cacheService;

    @Override
    public String calculate(String expression, Integer digits) {
        log.info(String.format("To evaluate expression %s with %s digits", expression, digits));
        MathExpression mathExpression = new MathExpression(expression, digits);
        Expression expression1 = null; 
        try{
            expression1 = new ExpressionBuilder(mathExpression.getExpression()).build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BadRequest("Error while creating expression, please check the expression");
        }
        String resultCached =  cacheService.pullResult(mathExpression);
        double result;
        if(resultCached!=null){
            result = Double.parseDouble(resultCached);
        }else{
            try{
                result = expression1.evaluate();
            }catch (Exception e){
                log.error(e.getMessage());
                throw new BadRequest("Error while evaluating expression, please check the expression");
            }
        }
            
        String evaluated = setSignificantDigits(result, digits);
        log.debug("Result: "+evaluated);
        MathResult mathResult = new MathResult(mathExpression, evaluated);
        cacheService.pushResult(mathResult);
        return evaluated;
    }
    
    private static String setSignificantDigits(double value, int significantDigits) {
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
