package com.github.ciferliu;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Money Calculator - <strong>non-threadsafe</strong>.
 * <p>
 * 注意：此类为<strong>非线程安全</strong>，请确保在单线程下使用。<br> 
 * 用法：
 * 
 * <pre class="code">
 * MoneyCalculator calculator = MoneyCalculator.fromRoundingMode(RoundingMode.HALF_UP);// 四舍五入
 * 
 * Money m = CurrencyFactory.get("USD").fromBasicUnitValue(100.00);// 用币种的基本单位（元）来构造：$100.00
 * Money m2 = CurrencyFactory.get("USD").fromMinorUnitValue(10000);// 用币种的最小单位（分）来构造：$100.00
 * 
 * Money result = calculator.init(m).add(m2).getResult();
 * double value = result.getBasicUnitValue();// 200.0
 * String strValue = result.getBasicUnitStringValue();// "200.00"
 * long minorUnitValue = result.getMinorUnitValue();// 20000
 * </pre>
 * 
 * @author Cifer Liu
 * @date 2020/03/18
 */
public class MoneyCalculator {
    private RoundingMode roundingMode = null;
    private Money result;

    private MoneyCalculator(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    /**
     * construct a calculator from the rounding mode
     * 
     * @param roundingMode
     * @return
     */
    public static MoneyCalculator fromRoundingMode(RoundingMode roundingMode) {
        if (roundingMode == null) {
            throw new IllegalArgumentException("roundingMode can't be null");
        }
        return new MoneyCalculator(roundingMode);
    }

    /**
     * init a value to calculator
     * 
     * @param m
     * @return
     * @throws IllegalArgumentException
     *             if m is null
     */
    public MoneyCalculator init(Money m) {
        if (m == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        round(m);
        result = m;
        return this;
    }

    /**
     * get the calculation result
     * 
     * @return
     */
    public Money getResult() {
        Money result = Money.rounded(this.result.getCurrency(), this.result.basicUnitValue());
        this.result = null;
        return result;
    }

    /**
     * clear the calculation result
     * 
     * @return
     */
    public MoneyCalculator clear() {
        this.result = null;
        return this;
    }

    /**
     * add
     * 
     * @param m
     *            augend value.
     * @return {@code this.value + m}
     * @throws IllegalArgumentException
     *             if arguments are illegal.
     */
    public MoneyCalculator add(Money m) {
        preCheck(m);
        result = Money.rounded(m.getCurrency(), result.basicUnitValue().add(m.basicUnitValue()));
        return this;
    }

    /**
     * subtract {@code (m1 - m2)}
     * 
     * @param m
     *            subtrahend value.
     * @return {@code this.value - m}
     * @throws IllegalArgumentException
     *             if arguments are illegal.
     */
    public MoneyCalculator subtract(Money m) {
        preCheck(m);
        result = Money.rounded(m.getCurrency(), result.basicUnitValue().subtract(m.basicUnitValue()));
        return this;
    }

    /**
     * multiply {@code (m * rate)}
     * 
     * @param rate
     *            multiplicand value.
     * @return {@code this.value * rate}
     * @throws IllegalArgumentException
     *             if arguments are illegal.
     */
    public MoneyCalculator multiply(double rate) {
        preCheck();
        BigDecimal bvalue = result.basicUnitValue().multiply(BigDecimal.valueOf(rate));
        bvalue = bvalue.divide(BigDecimal.ONE, result.getCurrency().getScale(), roundingMode);
        result = Money.rounded(result.getCurrency(), bvalue);
        return this;
    }

    /**
     * divide {@code (m / rate)}
     * 
     * @param rate
     *            divisor value.
     * @return {@code this.value / rate}
     * @throws IllegalArgumentException
     *             if rate is 0.
     */
    public MoneyCalculator divide(double rate) {
        preCheck();
        if (rate == 0) {
            throw new IllegalArgumentException("rate can't be zero");
        }
        BigDecimal bvalue =
            result.basicUnitValue().divide(BigDecimal.valueOf(rate), result.getCurrency().getScale(), roundingMode);
        result = Money.rounded(result.getCurrency(), bvalue);
        return this;
    }

    /**
     * FX {@code (m * rate)}
     * 
     * @param m
     * @param fxRate
     *            FX rate
     * @param targetCurrency
     *            target currency
     * @return a new currency money with value {@code m * rate}
     * @throws NullPointerException
     *             if m or targetCurrency is null.
     * @throws IllegalArgumentException
     *             if rate is 0.
     */
    public MoneyCalculator fxByMultiply(Currency targetCurrency, double fxRate) {
        preCheck();
        if (targetCurrency == null) {
            throw new IllegalArgumentException("targetCurrency can't be null");
        }
        // Currency is same, the rate is 1, and the result is the same value
        if (result.getCurrency().equals(targetCurrency)) {
            return this;
        }
        if (fxRate == 0) {
            throw new IllegalArgumentException("fxRate can't be zero");
        }

        BigDecimal value = result.basicUnitValue().multiply(BigDecimal.valueOf(fxRate));
        value = value.divide(BigDecimal.ONE, targetCurrency.getScale(), roundingMode);
        result = Money.rounded(targetCurrency, value);
        return this;
    }

    /**
     * FX {@code (m / rate)}
     * 
     * @param m
     * @param fxRate
     *            FX rate
     * @param targetCurrency
     *            target currency
     * @return a new currency money with value {@code m / rate}
     * @throws NullPointerException
     *             if m or targetCurrency is null.
     * @throws IllegalArgumentException
     *             if rate is 0.
     */
    public MoneyCalculator fxByDivide(Currency targetCurrency, double fxRate) {
        preCheck();
        if (targetCurrency == null) {
            throw new IllegalArgumentException("targetCurrency can't be null");
        }
        // Currency is same, the rate is 1, and the result is the same value
        if (result.getCurrency().equals(targetCurrency)) {
            return this;
        }
        if (fxRate == 0) {
            throw new IllegalArgumentException("fxRate can't be zero");
        }

        BigDecimal value =
            result.basicUnitValue().divide(BigDecimal.valueOf(fxRate), targetCurrency.getScale(), roundingMode);
        result = Money.rounded(targetCurrency, value);
        return this;
    }

    /**
     * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     * 
     * @param m1
     * @param m2
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
     *         than the second.
     * @throws NullPointerException
     *             if m1 or m2 is null.
     * @throws IllegalArgumentException
     *             if the currency of two parameters are not same.
     */
    public int compare(Money m1, Money m2) {
        if (m1 == null || m2 == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (!m1.getCurrency().equals(m2.getCurrency())) {
            throw new IllegalArgumentException("currency is not same, can't do the calculation");
        }
        round(m1);
        round(m2);
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

    private void preCheck() {
        if (this.result == null) {
            throw new IllegalArgumentException("please inoke 'init' method first");
        }
    }

    private void preCheck(Money m) {
        preCheck();
        if (m == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (!this.result.getCurrency().equals(m.getCurrency())) {
            throw new IllegalArgumentException("currency is not same, can't do the calculation");
        }
        round(m);
    }

    private void round(Money m) {
        if (!m.isRounded()) {
            m.rounded(m.basicUnitValue().divide(BigDecimal.ONE, m.getCurrency().getScale(), roundingMode));
        }
    }
}
