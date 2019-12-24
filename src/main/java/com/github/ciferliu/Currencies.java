package com.github.ciferliu;

/**
 * The factory of currency.
 * 
 * @author Cifer Liu
 * @since 1.0.0
 */
public class Currencies {
    private Currencies() {}

    /**
     * CNY (code = CNY, symbol = ¥, scale = 2) - Chinese Yuan(AKA: RMB) - 人民币.
     */
    public static final Currency CNY = new Currency("CNY", "¥", 2);

    /**
     * CNH (code = CNH, symbol = ¥, scale = 2) - Chinese Yuan in Hongkong(AKA: Offshore Renminbi) - 离岸人民币.
     */
    public static final Currency CNH = new Currency("CNH", "¥", 2);

    /**
     * HKD (code = HKD, symbol = HK$, scale = 2) - Hong Kong Dollar - 港币.
     */
    public static final Currency HKD = new Currency("HKD", "HK$", 2);

    /**
     * MOP (code = MOP, symbol = MOP$, scale = 2) - Macau pataca - 澳门币.
     */
    public static final Currency MOP = new Currency("MOP", "MOP$", 2);

    /**
     * TWD (code = TWD, symbol = NT$, scale = 2) - New Taiwan Dollar - 新台币.
     */
    public static final Currency TWD = new Currency("TWD", "NT$", 2);

    /**
     * JPY (code = JPY, symbol = ¥, scale = 0) - Japanese Yen - 日元.
     */
    public static final Currency JPY = new Currency("JPY", "¥", 0);

    /**
     * KRW (code = KRW, symbol = ₩, scale = 0) - South Korean Won - 韩元.
     */
    public static final Currency KRW = new Currency("KRW", "₩", 0);

    /**
     * KPW (code = KPW, symbol = ₩, scale = 2) - North Korean Won - 朝鲜圆.
     */
    public static final Currency KPW = new Currency("KPW", "₩", 2);

    /**
     * THB (code = THB, symbol = ฿, scale = 2) - Thailand Baht - 泰铢.
     */
    public static final Currency THB = new Currency("THB", "฿", 2);

    /**
     * PHP (code = PHP, symbol = ₱, scale = 2) - Philippine Peso - 菲律宾比索.
     */
    public static final Currency PHP = new Currency("PHP", "₱", 2);

    /**
     * VND (code = VND, symbol = ₫, scale = 0) - Vietnam Dong - 越南盾.
     */
    public static final Currency VND = new Currency("VND", "₫", 0);

    /**
     * SGD (code = SGD, symbol = S$, scale = 2) - Singapore Dollar - 新加坡元.
     */
    public static final Currency SGD = new Currency("SGD", "S$", 2);

    /**
     * MYR (code = MYR, symbol = RM, scale = 2) - Malaysian Ringgit - 马来西亚林吉特.
     */
    public static final Currency MYR = new Currency("MYR", "RM", 2);

    /**
     * IDR (code = IDR, symbol = Rp, scale = 2) - Indonesian rupiah - 印度尼西亚卢比.
     */
    public static final Currency IDR = new Currency("IDR", "Rp", 2);

    /**
     * INR (code = INR, symbol = ₹, scale = 2) - Indian Rupee - 印度卢比.
     */
    public static final Currency INR = new Currency("INR", "₹", 2);

    /**
     * RUB (code = RUB, symbol = ₽, scale = 2) - Russian ruble - 俄罗斯卢布.
     */
    public static final Currency RUB = new Currency("RUB", "₽", 2);

    /**
     * USD (code = USD, symbol = US$, scale = 2) - United States Dollar - 美元.
     */
    public static final Currency USD = new Currency("USD", "US$", 2);

    /**
     * EUR (code = EUR, symbol = €, scale = 2) - Euro - 欧元.
     */
    public static final Currency EUR = new Currency("EUR", "€", 2);

    /**
     * CAD (code = CAD, symbol = Can$, scale = 2) - Canadian Dollar - 加元.
     */
    public static final Currency CAD = new Currency("CAD", "Can$", 2);

    /**
     * AUD (code = AUD, symbol = A$, scale = 2) - Australian Dollar - 澳大利亚元.
     */
    public static final Currency AUD = new Currency("AUD", "A$", 2);

    /**
     * BRL (code = BRL, symbol = R$, scale = 2) - Brazilian real - 巴西雷亚尔.
     */
    public static final Currency BRL = new Currency("BRL", "R$", 2);

    /**
     * MXN (code = MXN, symbol = Mex$, scale = 2) - Mexican peso - 墨西哥比索.
     */
    public static final Currency MXN = new Currency("MXN", "Mex$", 2);
}
