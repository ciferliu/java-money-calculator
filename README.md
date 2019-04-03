# java-money-calculator
A java tool for money calculate

***
## Requires
jdk 1.5+

***
## Features
+ ISO 4217 standard currencies
 + user-defined currencies
 + default rounding mode: HALF_EVEN (AKA: Banker's rounding)
 + support all of rounding modes of java.math.RoundingMode
 + operations: add/subtract/multiply/divide/fxByMultiply/fxByDivide/compare

***
## Usage
```java
Money m1 = Currencies.USD.fromBasicUnitValue(1.01);//build a USD money instance by using basic unit, which is dollor.
Money m2 = Currencies.USD.fromMinorUnitValue(1.09);//build a USD money instance by using minor unit, which is cent.

Money result = MoneyCalculator.add(m1, m2);

double dollorValue = result.getBasicUnitValue();//2.1
String dollorValueStr = result.getBasicUnitValueString();//"2.10"

long centValue = result.getMinorUnitValue();//210

String resultStr = result.toString();//"USD 2.10"
```

If your currency is not supported by Currencies, you can create it:
```java
//currency code=ETH
//currency symbol=Ether
//currency scale=18
//roundingMode=HALF_UP
Currency ETH = new Currency("ETH", "Ether", 18, RoundingMode.HALF_UP);

ETH.fromBasicUnitValue(1);//1 Ether
```