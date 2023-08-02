package com.example.MarTrustBE.data;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateConvertResponse {

    public Boolean success;

    private Map<String, Object> query;

    private Map<String, Object> info;

    private Map<String, Object> historical;

    private String date;

    private Double result;

}
