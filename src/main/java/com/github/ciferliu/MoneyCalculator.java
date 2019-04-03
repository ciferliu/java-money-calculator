package com.github.ciferliu;

import java.math.BigDecimal;

public class MoneyCalculator {
  private MoneyCalculator() {}

  /**
   * add {@code (m1 + m2)}
   * 
   * @param m1 addend value.
   * @param m2 augend value.
   * @return {@code m1 + m2}
   * @throws NullPointerException if m1 or m2 is null.
   * @throws IllegalArgumentException if the currency of two parameters are not same.
   */
  public static Money add(Money m1, Money m2) {
    if (!m1.getCurrency().equals(m2.getCurrency())) {
      throw new IllegalArgumentException("currency is not same");
    }
    return new Money(m1.getCurrency(), m1.basicUnitValue().add(m2.basicUnitValue()));
  }

  /**
   * subtract {@code (m1 - m2)}
   * 
   * @param m1 minuend value.
   * @param m2 subtrahend value.
   * @return {@code m1 - m2}
   * @throws NullPointerException if m1 or m2 is null.
   * @throws IllegalArgumentException if the currency of two parameters are not same.
   */
  public static Money subtract(Money m1, Money m2) {
    if (!m1.getCurrency().equals(m2.getCurrency())) {
      throw new IllegalArgumentException("currency is not same");
    }
    return new Money(m1.getCurrency(), m1.basicUnitValue().subtract(m2.basicUnitValue()));
  }

  /**
   * multiply {@code (m * rate)}
   * 
   * @param m multiplier value.
   * @param rate multiplicand value.
   * @return {@code m * rate}
   * @throws NullPointerException if m is null.
   */
  public static Money multiply(Money m, double rate) {
    BigDecimal value = m.basicUnitValue().multiply(BigDecimal.valueOf(rate));
    value = value.divide(BigDecimal.ONE, m.getCurrency().getScale(), m.getCurrency().getRoundingMode());
    return new Money(m.getCurrency(), value);
  }

  /**
   * divide {@code (m / rate)}
   * 
   * @param m dividend value.
   * @param rate divisor value.
   * @return {@code m / rate}
   * @throws NullPointerException if m is null.
   * @throws IllegalArgumentException if rate is 0.
   */
  public static Money divide(Money m, double rate) {
    if (rate == 0) {
      throw new IllegalArgumentException("rate can't be zero");
    }
    BigDecimal value = m.basicUnitValue().divide(BigDecimal.valueOf(rate), m.getCurrency().getScale(), m.getCurrency().getRoundingMode());
    return new Money(m.getCurrency(), value);
  }

  /**
   * FX {@code (m * rate)}
   * 
   * @param m
   * @param rate FX rate
   * @param targetCurrency target currency
   * @return a new currency money with value {@code m * rate}
   * @throws NullPointerException if m or targetCurrency is null.
   * @throws IllegalArgumentException if rate is 0.
   */
  public static Money fxByMultiply(Money m, double rate, Currency targetCurrency) {
    if (rate == 0) {
      throw new IllegalArgumentException("rate can't be zero");
    }
    BigDecimal value = m.basicUnitValue().multiply(BigDecimal.valueOf(rate));
    value = value.divide(BigDecimal.ONE, m.getCurrency().getScale(), m.getCurrency().getRoundingMode());
    return new Money(targetCurrency, value);
  }

  /**
   * FX {@code (m / rate)}
   * 
   * @param m
   * @param rate FX rate
   * @param targetCurrency target currency
   * @return a new currency money with value {@code m / rate}
   * @throws NullPointerException if m or targetCurrency is null.
   * @throws IllegalArgumentException if rate is 0.
   */
  public static Money fxByDivide(Money m, double rate, Currency targetCurrency) {
    if (rate == 0) {
      throw new IllegalArgumentException("rate can't be zero");
    }
    BigDecimal value = m.basicUnitValue().divide(BigDecimal.valueOf(rate), m.getCurrency().getScale(), m.getCurrency().getRoundingMode());
    return new Money(targetCurrency, value);
  }

  /**
   * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   * 
   * @param m1
   * @param m2
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   * @throws NullPointerException if m1 or m2 is null.
   * @throws IllegalArgumentException if the currency of two parameters are not same.
   */
  public static int compare(Money m1, Money m2) {
    if (!m1.getCurrency().equals(m2.getCurrency())) {
      throw new IllegalArgumentException("currency is not same");
    }
    long m1MinorUnitValue = m1.getMinorUnitValue();
    long m2MinorUnitValue = m2.getMinorUnitValue();
    if (m1MinorUnitValue < m2MinorUnitValue) {
      return -1;
    } else if (m1MinorUnitValue == m2MinorUnitValue) {
      return 0;
    } else {
      return 1;
    }
  }
}
