package com.github.alesisjoan.slingr.domain;

import java.util.Objects;

public class MathResult {
    
    private final MathExpression mathExpression;
    
    private final String result;

    public MathResult(MathExpression mathExpression, String result) {
        this.mathExpression = mathExpression;
        this.result = result;
    }

    public MathExpression getMathExpression() {
        return mathExpression;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        MathResult that = (MathResult) o;
        return mathExpression.equals(that.mathExpression) &&
                result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathExpression, result);
    }

    @Override
    public String toString() {
        return "{" +
                "\"expression\":" + mathExpression.toString() + ", " +
                "\"result\":" + "\"" + result + "\"" +
                "}";
    }
}
