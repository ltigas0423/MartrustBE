package com.example.martrustbe.data;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateLatestResponse {

    private Boolean success;

    private long timestamp;

    private String base;

    private String date;

    private Map<String, Double> rates;

}
