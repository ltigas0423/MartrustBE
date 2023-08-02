package com.example.MarTrustBE.service;

import com.example.MarTrustBE.data.ExchangeRateDTO;

import java.util.List;
import java.util.Map;

public interface ExchangeRateService {

    Map<String, Double> getExchangeRates();

    Double getExchangeRate(String from, String to);

    Double convert(String from, String to, Double amount);
}
