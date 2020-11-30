package com.github.alesisjoan.slingr.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MathExpressionValidator implements
        ConstraintValidator<MathExpressionConstraint, String> {

    public static final String MATH_EXPRESSION_REGEX 
            = "^([(\bsqrt\\(\b)|\\-|\\+|*|\\/|\\(|\\)\\d(.\\d)?])*$";

    @Override
    public void initialize(MathExpressionConstraint mathExpression) {
    }

    @Override
    public boolean isValid(String mathExpression,
                           ConstraintValidatorContext cxt) {
        return mathExpression!=null
                && !mathExpression.isEmpty()
                && mathExpression.matches(MATH_EXPRESSION_REGEX);
    }
}


