package com.github.ciferliu;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * currency context: currency alpha code, symbol and scale.
 * 
 * @author Cifer Liu
 * @since 1.0.0
 */
public class Currency {

  /**
   * The alpha code of currency.
   */
  private String code;

  /**
   * The symbol of currency.
   */
  private String symbol;

  /**
   * The number of digits after the decimal separator.
   */
  private int scale;

  /**
   * Rounding mode, the default rounding mode is HALF_EVEN (AKA: Banker's rounding).
   */
  private RoundingMode roundingMode = RoundingMode.HALF_EVEN;

  /**
   * Currency
   * 
   * @param code - the alpha code of currency, <strong>must not be null</strong>.
   * @param symbol - the symbol of currency, <strong>can be null</strong>.
   * @param scale - the number of digits after the decimal separator, <strong>must not be negative</strong>.
   * @param roundingMode - the rounding mode, <strong>if null, HALF_EVEN(AKA: Banker's rounding) will be used</strong>.
   */
  public Currency(String code, String symbol, int scale, RoundingMode roundingMode) {
    if (code != null) {
      code = code.trim().toUpperCase();
    }
    if (code == null || code.isEmpty()) {
      throw new IllegalArgumentException("code can't be null");
    }
    if (scale < 0) {
      throw new IllegalArgumentException("scale can't be negative");
    }

    this.code = code;
    this.symbol = symbol;
    this.scale = scale;
    if (roundingMode != null) {
      this.roundingMode = roundingMode;
    }
  }

  /**
   * package scope constructor - to avoid parameter validation
   */
  Currency(String code, String symbol, int scale) {
    this.code = code;
    this.symbol = symbol;
    this.scale = scale;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCode() {
    return code;
  }

  public String getSymbol() {
    return symbol;
  }

  public int getScale() {
    return scale;
  }

  public RoundingMode getRoundingMode() {
    return roundingMode;
  }

  /**
   * build a money instance by using the currency's basic unit.
   * <p>
   * for example, "USD 1.00", the basicUnitValue will be 1
   * 
   * @param basicUnitValue
   * @return
   */
  public Money fromBasicUnitValue(double basicUnitValue) {
    if (Double.isInfinite(basicUnitValue) || Double.isNaN(basicUnitValue)) {
      throw new IllegalArgumentException("basicUnitValue can't be Infinite or NaN");
    }
    return new Money(this, BigDecimal.valueOf(basicUnitValue).divide(BigDecimal.ONE, scale, roundingMode));
  }

  /**
   * build a money instance by using the currency's minor unit.
   * <p>
   * for example, "USD 1.00", the minorUnitValue will be 100 (cent)
   * 
   * @param minorUnitValue
   * @return
   */
  public Money fromMinorUnitValue(long minorUnitValue) {
    return new Money(this, BigDecimal.valueOf(minorUnitValue).divide(BigDecimal.TEN.pow(scale), scale, roundingMode));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + scale;
    result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
    Currency other = (Currency) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (scale != other.scale)
      return false;
    if (symbol == null) {
      if (other.symbol != null)
        return false;
    } else if (!symbol.equals(other.symbol))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Currency [code=" + code + ", symbol=" + symbol + ", scale=" + scale + ", roundingMode=" + roundingMode + "]";
  }
}
