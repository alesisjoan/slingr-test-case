package com.github.alesisjoan.slingr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MathExpression
 */
@Validated
public class MathExpression {
  @JsonProperty("expression")
  private String expression = null;

  @JsonProperty("digits")
  private Integer digits = null;

  public MathExpression expression(String expression) {
    this.expression = expression;
    return this;
  }


  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public MathExpression digits(Integer digits) {
    this.digits = digits;
    return this;
  }

  public Integer getDigits() {
    return digits;
  }

  public void setDigits(Integer digits) {
    this.digits = digits;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MathExpression mathExpression = (MathExpression) o;
    return Objects.equals(this.expression, mathExpression.expression) &&
        Objects.equals(this.digits, mathExpression.digits);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expression, digits);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MathExpression {\n");
    
    sb.append("    expression: ").append(toIndentedString(expression)).append("\n");
    sb.append("    digits: ").append(toIndentedString(digits)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

