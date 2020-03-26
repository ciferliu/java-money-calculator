package com.github.ciferliu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

public class MoneyCalculatorTest {
    private MoneyCalculator calculator = null;

    @Before
    public void init() {
        calculator = MoneyCalculator.fromRoundingMode(RoundingMode.HALF_UP);
    }

    @Test
    public void testFromRoundingMode() {
        MoneyCalculator calculator1 = MoneyCalculator.fromRoundingMode(RoundingMode.HALF_UP);
        assertTrue(calculator == calculator1);

        MoneyCalculator calculator2 = MoneyCalculator.fromRoundingMode(RoundingMode.HALF_EVEN);
        assertTrue(calculator != calculator2);
    }

    @Test
    public void testAdd() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.01);
        Money m2 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.09);
        assertEquals(210L, calculator.init(m1).add(m2).getResult().getMinorUnitValue());
    }

    @Test
    public void testSubtract() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.01);
        Money m2 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.09);
        assertEquals(8L, calculator.init(m2).subtract(m1).getResult().getMinorUnitValue());
    }

    @Test
    public void testMultiply() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.05);
        assertEquals(9L, calculator.init(m1).multiply(0.09).getResult().getMinorUnitValue());

        m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.06);
        assertEquals(10L, calculator.init(m1).multiply(0.09).getResult().getMinorUnitValue());
    }

    @Test
    public void testDivide() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.01);
        assertEquals(101L, calculator.init(m1).divide(1).getResult().getMinorUnitValue());
    }

    @Test
    public void testFxByMultiply() {
        Money m1 = CurrencyFactory.get("USD").fromBasicUnitValue(1.01);
        Money result = calculator.init(m1).fxByMultiply(CurrencyFactory.get("CNY"), 6.4567).getResult();
        assertEquals(CurrencyFactory.get("CNY"), result.getCurrency());
        assertEquals(652, result.getMinorUnitValue());
    }

    @Test
    public void testFxByDivide() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(100);
        Money result = calculator.init(m1).fxByDivide(CurrencyFactory.get("USD"), 6.4567).getResult();
        assertEquals(CurrencyFactory.get("USD"), result.getCurrency());
        assertEquals(1549L, result.getMinorUnitValue());
    }

    @Test
    public void testCompare() {
        Money m1 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.01);
        Money m2 = CurrencyFactory.get("CNY").fromBasicUnitValue(1.09);
        assertEquals(-1, calculator.compare(m1, m2));
        assertEquals(0, calculator.compare(m1, CurrencyFactory.get("CNY").fromBasicUnitValue(1.01)));
        assertEquals(1, calculator.compare(m2, m1));
    }

}
