package com.example.martrustbe;

import com.google.common.collect.Maps;

import java.util.Map;

public class ExchangeRatesUtil {

    public static Map<String, Double> getRates() {
        Map<String, Double> tempRates = Maps.newHashMap();

        tempRates.put("USD", 1.096954);
        tempRates.put("EUR", 1.0);
        tempRates.put("JPY", 156.574864);
        tempRates.put("GBP", 0.85535);
        tempRates.put("AUD", 1.653148);
        tempRates.put("CAD", 1.454002);
        tempRates.put("CHF", 0.960246);
        tempRates.put("CNY", 7.861759);
        tempRates.put("SEK", 11.626402);
        tempRates.put("NZD", 1.78068);
        tempRates.put("KRW", 1413.283054);
        tempRates.put("SGD", 1.463178);
        tempRates.put("NOK", 11.196229);
        tempRates.put("MXN", 18.408374);
        tempRates.put("INR", 90.248131);
        tempRates.put("RUB", 100.820972);
        tempRates.put("ZAR", 19.802723);
        tempRates.put("TRY", 29.577511);
        tempRates.put("BRL", 5.182563);
        tempRates.put("TWD", 34.624045);
        tempRates.put("PHP", 60.18);

        return tempRates;
    }
}
