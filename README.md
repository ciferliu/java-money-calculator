# java-money-calculator
A java tool for money calculate

***
## Requires
jdk 1.7+

***
## Features
+ ```ISO 4217``` standard currencies
+ user-defined currencies
+ support all of rounding modes of java.math.RoundingMode
+ operations: add/subtract/multiply/divide/fxByMultiply/fxByDivide/compare
+ Stream API: ```calculator.init(m1).add(m2).multiply(n).getResult()```

***
## Usage
```java
Money m1 = CurrencyFactory.get("USD").fromBasicUnitValue(1.01);//build a USD money instance by using basic unit, which is dollor.
Money m2 = CurrencyFactory.get("USD").fromMinorUnitValue(109);//build a USD money instance by using minor unit, which is cent.

MoneyCalculator calculator = MoneyCalculator.fromRoundingMode(RoundingMode.HALF_UP);
Money result = calculator.init(m1).add(m2).getResult();

double dollorValue = result.getBasicUnitValue();//2.1
String dollorValueStr = result.getBasicUnitValueString();//"2.10"

long centValue = result.getMinorUnitValue();//210

String resultStr = result.toString();//"USD 2.10"
```

By default, ```CurrencyFactory``` only supported two currencies: ```CNY``` and ```USD```.<br>
But, it's easy to extend, by adding a JSON config file named ```currency_factory_config.json``` in your CLASSPATH, and the config file format like this:
```json
[
	{"code":"CNY","symbol":"¥","scale":2},
    {"code":"USD","symbol":"$","scale":2}
]
```