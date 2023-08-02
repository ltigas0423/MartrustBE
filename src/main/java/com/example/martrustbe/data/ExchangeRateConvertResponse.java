package com.example.martrustbe.data;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateConvertResponse {

    private Boolean success;

    private Map<String, Object> query;

    private Map<String, Object> info;

    private Map<String, Object> historical;

    private String date;

    private Double result;

}
