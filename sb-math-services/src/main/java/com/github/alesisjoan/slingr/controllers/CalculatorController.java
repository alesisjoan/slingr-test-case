package com.github.alesisjoan.slingr.controllers;

import com.github.alesisjoan.slingr.config.BadRequest;
import com.github.alesisjoan.slingr.domain.MathExpression;
import com.github.alesisjoan.slingr.services.Calculator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class CalculatorController {

    private static final Logger log = LoggerFactory
            .getLogger(CalculatorController.class);
    
    private static final Integer DEFAULT_DIGITS = 0; // TODO extract from properties
    
    @Autowired
    Calculator calculator;

    @ApiOperation(value = "calculate expression", nickname = "expressionsGet", notes = "", tags={ "expressions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error / Math exception") })
    @RequestMapping(value = "/expressions", method = RequestMethod.GET)
    ResponseEntity<String> expressionsGet(@NotNull @ApiParam(value = "Expression to be evaluated", required = true) 
                                        @Valid @RequestParam(value = "expression", required = true) String expression,
                                        @ApiParam(value = "number of significant digits in formatted output. It is optional.")
                                        @Valid @RequestParam(value = "digits", required = false, defaultValue = "-1") Integer digits){
        
        if(digits < 1 ){
            digits = DEFAULT_DIGITS;
        }                                    
        String result = calculator.calculate(expression, digits);                                            
        return ResponseEntity.ok(result);                                        
    }


    @ApiOperation(value = "calculate expression", nickname = "expressionsPost", notes = "", tags={ "expressions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error / Math exception") })
    @RequestMapping(value = "/expressions",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<String> expressionsPost(@ApiParam(value = "Expression Object" ,required=true )  @Valid @RequestBody MathExpression body) {

        String result = calculator.calculate(body.getExpression(), body.getDigits() < 1 ? DEFAULT_DIGITS : body.getDigits());
        return ResponseEntity.ok(result);
    }
}
