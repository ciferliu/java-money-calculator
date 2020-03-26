package com.github.ciferliu;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Immutable, thread-safe, amount with currency context.
 * 
 * @author Cifer Liu
 * @since 1.0.0
 */
public class Money {
    private Currency currency;
    private BigDecimal basicUnitValue;
    private boolean isRounded;

    /**
     * private scope constructor
     * 
     * @param currency
     * @param minorUnitValue
     */
    private Money(Currency currency, BigDecimal basicUnitValue, boolean isRounded) {
        this.currency = currency;
        this.basicUnitValue = basicUnitValue;
        this.isRounded = isRounded;
    }

    static Money unRound(Currency currency, BigDecimal basicUnitValue) {
        return new Money(currency, basicUnitValue, false);
    }

    static Money rounded(Currency currency, BigDecimal basicUnitValue) {
        return new Money(currency, basicUnitValue, true);
    }

    void rounded(BigDecimal basicUnitValue) {
        this.basicUnitValue = basicUnitValue;
        this.isRounded = true;
    }

    BigDecimal basicUnitValue() {
        return basicUnitValue;
    }

    boolean isRounded() {
        return isRounded;
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
        return Objects.hash(basicUnitValue, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Money)) {
            return false;
        }
        Money other = (Money)obj;
        return Objects.equals(basicUnitValue, other.basicUnitValue) && Objects.equals(currency, other.currency);
    }

}
