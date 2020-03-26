package com.github.ciferliu;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Immutable, thread-safe, currency context: currency alpha code, symbol and scale.
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
     * Currency constructor
     * 
     * @param code
     *            - The alpha code of currency(ISO_4217 codes or self-defined code), can't be null
     * @param symbol
     *            - can't be null
     * @param scale
     *            - can't less than 0
     */
    public Currency(String code, String symbol, int scale) {
        if (code == null || symbol == null || scale < 0) {
            throw new IllegalArgumentException("param is illegal");
        }

        this.code = code;
        this.symbol = symbol;
        this.scale = scale;
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
        return Money.unRound(this, BigDecimal.valueOf(basicUnitValue));
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
        return Money.unRound(this, BigDecimal.valueOf(minorUnitValue).divide(BigDecimal.TEN.pow(scale)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Currency)) {
            return false;
        }
        Currency other = (Currency)obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(70);
        builder.append("Currency [code=");
        builder.append(code);
        builder.append(", symbol=");
        builder.append(symbol);
        builder.append(", scale=");
        builder.append(scale);
        builder.append("]");
        return builder.toString();
    }

}
