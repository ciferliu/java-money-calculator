package com.github.ciferliu;

import java.math.BigDecimal;

public class Money {
  private Currency currency;
  private BigDecimal basicUnitValue;

  /**
   * package scope constructor
   * 
   * @param currency
   * @param minorUnitValue
   */
  Money(Currency currency, BigDecimal basicUnitValue) {
    this.currency = currency;
    this.basicUnitValue = basicUnitValue;
  }

  BigDecimal basicUnitValue() {
    return basicUnitValue;
  }

  public Currency getCurrency() {
    return currency;
  }

  public double getBasicUnitValue() {
    return basicUnitValue.doubleValue();
  }

  public String getBasicUnitValueString() {
    return basicUnitValue.toString();
  }

  public long getMinorUnitValue() {
    return basicUnitValue.multiply(BigDecimal.TEN.pow(currency.getScale())).longValue();
  }

  @Override
  public String toString() {
    return String.join(" ", currency.getCode(), getBasicUnitValueString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((basicUnitValue == null) ? 0 : basicUnitValue.hashCode());
    result = prime * result + ((currency == null) ? 0 : currency.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;
    if (basicUnitValue == null) {
      if (other.basicUnitValue != null)
        return false;
    } else if (basicUnitValue.doubleValue() != other.basicUnitValue.doubleValue())
      return false;
    if (currency == null) {
      if (other.currency != null)
        return false;
    } else if (!currency.equals(other.currency))
      return false;
    return true;
  }

}
