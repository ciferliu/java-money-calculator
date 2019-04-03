package com.github.ciferliu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CurrencyTest {

  @Test
  public void testFromBasicUnitValue() {
    Money m = Currencies.CNY.fromBasicUnitValue(1.00);
    assertTrue(1 == m.getBasicUnitValue());

    m = Currencies.CNY.fromBasicUnitValue(1.45);
    assertTrue(1.45 == m.getBasicUnitValue());

    m = Currencies.CNY.fromBasicUnitValue(1.454);
    assertTrue(1.45 == m.getBasicUnitValue());

    m = Currencies.CNY.fromBasicUnitValue(1.455);
    assertTrue(1.46 == m.getBasicUnitValue());

    m = Currencies.CNY.fromBasicUnitValue(1.445);
    assertTrue(1.44 == m.getBasicUnitValue());

    m = Currencies.CNY.fromBasicUnitValue(1.4451);
    assertTrue(1.45 == m.getBasicUnitValue());
  }

  @Test
  public void testFromMinorUnitValue() {
    Money m = Currencies.CNY.fromMinorUnitValue(100);
    assertTrue(100 == m.getMinorUnitValue());
    assertTrue(1 == m.getBasicUnitValue());
    assertEquals("CNY 1.00", m.toString());

    m = Currencies.CNY.fromMinorUnitValue(101);
    assertTrue(101 == m.getMinorUnitValue());
    assertTrue(1.01 == m.getBasicUnitValue());
    assertEquals("CNY 1.01", m.toString());

    m = Currencies.CNY.fromMinorUnitValue(109);
    assertTrue(109 == m.getMinorUnitValue());
    assertTrue(1.09 == m.getBasicUnitValue());
    assertEquals("CNY 1.09", m.toString());
  }
}
