package com.github.ciferliu;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

/**
 * The factory of currency.
 * <p>
 * 内置支持币种：
 * <ul>
 * <li>CNY - 人民币（symbol="¥", scale=2）</li>
 * <li>USD - 美元（symbol="US$", scale=2）</li>
 * </ul>
 * 应用可扩展和覆盖内置币种配置，扩展方法：将名为"currency_factory_config.json"配置文件放置在classpath根目录下
 * 
 * <pre>
 * 路径如下：
 * app
 *  |----src/main/java
 *  |       |----com.xxx.yyy.java
 *  |----src/main/resources
 *  |       |----currency_factory_config.json
 *  <br>
 *  文件内容是JSON Array，格式如下：
 *  [
 *      {"code":"CNY","symbol":"¥","scale":2},
 *      {"code":"USD","symbol":"US$","scale":2},
 *      {"code":"INR","symbol":"₹","scale":2}
 *  ]
 * </pre>
 * 
 * @author Cifer Liu
 * @date 2020/03/18
 */
public class CurrencyFactory {
    /**
     * 应用扩展配置文件名称
     */
    public static final String CLASSPATH_CONFIG_FILE_NAME = "currency_factory_config.json";
    private static final Map<String, Currency> CACHE = new ConcurrentHashMap<>();
    static {
        CACHE.put("CNY", new Currency("CNY", "¥", 2));// 人民币
        CACHE.put("USD", new Currency("USD", "US$", 2));// 美元
        init();
    }

    private CurrencyFactory() {}

    private static void init() {
        URL url = null;
        try {
            url = Resources.getResource(CLASSPATH_CONFIG_FILE_NAME);
        } catch (IllegalArgumentException e) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(url);
        } catch (Exception e) {
            throw new RuntimeException("parse currencies config file exception", e.getCause());
        }
        int count = jsonNode.size();
        for (int i = 0; i < count; i++) {
            JsonNode tmp = jsonNode.get(i);
            String code = tmp.get("code").asText();
            String symbol = tmp.get("symbol").asText();
            int scale = tmp.get("scale").asInt();
            if (code == null || symbol == null || scale < 0) {
                continue;
            }
            code = code.toUpperCase();
            CACHE.put(code, new Currency(code, symbol, scale));
        }
    }

    public static Currency get(String currencyCode) {
        currencyCode = currencyCode == null ? null : currencyCode.trim();
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new IllegalArgumentException("param is illegal");
        }
        currencyCode = currencyCode.toUpperCase();
        return CACHE.get(currencyCode);
    }
}
