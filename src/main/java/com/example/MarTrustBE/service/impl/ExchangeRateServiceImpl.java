package com.example.MarTrustBE.service.impl;

import com.example.MarTrustBE.data.ExchangeRateLatestResponse;
import com.example.MarTrustBE.feign.ExchangeRatesClient;
import com.example.MarTrustBE.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class    ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRatesClient exchangeRatesClient;

    @Value("${access-key}")
    private String accessKey;

    @Override
    public Map<String, Double> getExchangeRates() {
        ExchangeRateLatestResponse response = exchangeRatesClient.getLatest(accessKey);

        return response.getRates();
    }

    @Override
    public Double getExchangeRate(String from, String to) {
//        ExchangeRateLatestResponse response = exchangeRatesClient.getLatest(accessKey);

        return convertUsingLatest(from, to, 1d);
    }

    @Override
    public Double convert(String from, String to, Double amount) {
//        ExchangeRateConvertResponse response = exchangeRatesClient.convert(accessKey, from, to, amount);
        return convertUsingLatest(from, to, amount);
    }

    //Using this because my access_key does not support the convert API
    private Double convertUsingLatest(String from, String to, Double amount) {
        Map<String, Double> latestRates = getExchangeRates();
        Double fromRate = latestRates.get(from);
        Double toRate = latestRates.get(to);

        Double exchangeRate = toRate / fromRate;

        return amount * exchangeRate;
    }
}
