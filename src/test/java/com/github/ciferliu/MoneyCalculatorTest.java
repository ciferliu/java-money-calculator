package com.github.ciferliu;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MoneyCalculatorTest {

  @Test
  public void testAdd() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(1.01);
    Money m2 = Currencies.CNY.fromBasicUnitValue(1.09);
    assertEquals(Currencies.CNY.fromBasicUnitValue(2.1), MoneyCalculator.add(m1, m2));
  }

  @Test
  public void testSubtract() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(1.01);
    Money m2 = Currencies.CNY.fromBasicUnitValue(1.09);
    assertEquals(Currencies.CNY.fromBasicUnitValue(0.08), MoneyCalculator.subtract(m2, m1));
  }


  @Test
  public void testMultiply() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(1.05);
    assertEquals(Currencies.CNY.fromBasicUnitValue(0.09), MoneyCalculator.multiply(m1, 0.09));

    m1 = Currencies.CNY.fromBasicUnitValue(1.06);
    assertEquals(Currencies.CNY.fromBasicUnitValue(0.1), MoneyCalculator.multiply(m1, 0.09));
  }


  @Test
  public void testDivide() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(1.01);
    assertEquals(Currencies.CNY.fromBasicUnitValue(1.01), MoneyCalculator.divide(m1, 1));
  }

  @Test
  public void testFxByMultiply() {
    Money m1 = Currencies.USD.fromBasicUnitValue(1.01);
    assertEquals(Currencies.CNY.fromBasicUnitValue(6.52), MoneyCalculator.fxByMultiply(m1, 6.4567, Currencies.CNY));
  }

  @Test
  public void testFxByDivide() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(100);
    assertEquals(Currencies.USD.fromBasicUnitValue(15.49), MoneyCalculator.fxByDivide(m1, 6.4567, Currencies.USD));
  }

  @Test
  public void testCompare() {
    Money m1 = Currencies.CNY.fromBasicUnitValue(1.01);
    Money m2 = Currencies.CNY.fromBasicUnitValue(1.09);
    assertEquals(-1, MoneyCalculator.compare(m1, m2));
    assertEquals(0, MoneyCalculator.compare(m1, Currencies.CNY.fromBasicUnitValue(1.01)));
    assertEquals(1, MoneyCalculator.compare(m2, m1));
  }

}
