package com.example.martrustbe.service;

import java.util.Map;

public interface ExchangeRateService {

    Map<String, Double> getExchangeRates();

    Double getExchangeRate(String from, String to);

    Double convert(String from, String to, Double amount);
}
