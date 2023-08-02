package com.example.MarTrustBE;

import com.example.MarTrustBE.data.ExchangeRateLatestResponse;
import com.example.MarTrustBE.feign.ExchangeRatesClient;
import com.example.MarTrustBE.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExchangeRateServiceImplTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @MockBean
    private ExchangeRatesClient exchangeRatesClient;

    @Test
    public void getExchangeRates() {
        when(exchangeRatesClient.getLatest(any())).thenReturn(getTempResponse());

        var response = exchangeRateService.getExchangeRates();

        assertEquals(response.size(), getTempResponse().getRates().size());
        assertEquals(response.get("USD"), getTempResponse().getRates().get("USD"));
    }

    @Test
    public void getExchangeRate() {
        when(exchangeRatesClient.getLatest(any())).thenReturn(getTempResponse());

        Double exchangeRate = exchangeRateService.getExchangeRate("USD", "PHP");

        assertEquals(exchangeRate, convertRates("USD", "PHP", 1d));
    }

    @Test
    public void convert() {
        when(exchangeRatesClient.getLatest(any())).thenReturn(getTempResponse());

        assertEquals(exchangeRateService.convert("USD", "PHP", 25.5), convertRates("USD", "PHP", 25.5));
    }


    @BeforeAll
    private ExchangeRateLatestResponse getTempResponse() {
        ExchangeRateLatestResponse response = new ExchangeRateLatestResponse();
        response.setRates(ExchangeRatesUtil.getRates());
        response.setBase("Base");
        response.setDate(new Date().toString());
        response.setSuccess(true);
        response.setTimestamp(new Date().toInstant().toEpochMilli());
        return response;
    }

    private Double convertRates(String from, String to, Double amount) {
        Map<String, Double> latestRates = getTempResponse().getRates();
        Double fromRate = latestRates.get(from);
        Double toRate = latestRates.get(to);

        Double exchangeRate = toRate / fromRate;

        return amount * exchangeRate;
    }
}
