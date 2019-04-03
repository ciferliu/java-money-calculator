package com.github.ciferliu;

import java.math.BigDecimal;

/**
 * Immutable, amount with currency context.
 * 
 * @author Cifer Liu
 * @since 1.0.0
 */
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

  /**
   * get currency context
   * 
   * @return Currency
   */
  public Currency getCurrency() {
    return currency;
  }

  /**
   * get amount in currency's basic unit
   * 
   * @return amount in currency's basic unit
   */
  public double getBasicUnitValue() {
    return basicUnitValue.doubleValue();
  }

  /**
   * get amount in currency's basic unit
   * 
   * @return amount in currency's basic unit
   */
  public String getBasicUnitValueString() {
    return basicUnitValue.toString();
  }

  /**
   * get amount in currency's minor unit
   * 
   * @return amount in currency's minor unit
   */
  public long getMinorUnitValue() {
    return basicUnitValue.multiply(BigDecimal.TEN.pow(currency.getScale())).longValue();
  }

  /**
   * money string.<br />
   * format: currency code + whitespace + amount in currency's basic unit, e.g., <code>USD 1.00</code>
   * 
   * @return string format like 'USD 1.00'
   */
  @Override
  public String toString() {
    return new StringBuilder(currency.getCode()).append(' ').append(getBasicUnitValueString()).toString();
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
