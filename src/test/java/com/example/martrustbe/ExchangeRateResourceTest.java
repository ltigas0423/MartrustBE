package com.example.martrustbe;


import com.example.martrustbe.resource.ExchangeRateResource;
import com.example.martrustbe.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeRateResource.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@Slf4j
public class ExchangeRateResourceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Test
    public void getExchangeRates() throws Exception {
        when(exchangeRateService.getExchangeRates()).thenReturn(ExchangeRatesUtil.getRates());

        this.mockMvc.perform(get("/exchange-rate/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getExchangeRate() throws Exception {
        var from = "USD";
        var to = "PHP";
        when(exchangeRateService.getExchangeRate(from, to)).thenReturn(convertRates(from, to, 25d));

        this.mockMvc.perform(get("/exchange-rate")
                        .param("from", from)
                        .param("to", to)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void convert() throws Exception {
        var from = "USD";
        var to = "PHP";
        var amount = 25d;
        when(exchangeRateService.convert(from, to, amount)).thenReturn(convertRates(from, to, amount));

        this.mockMvc.perform(post("/exchange-rate/convert")
                        .param("from", from)
                        .param("to", to)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private Double convertRates(String from, String to, Double amount) {
        Map<String, Double> latestRates = ExchangeRatesUtil.getRates();
        Double fromRate = latestRates.get(from);
        Double toRate = latestRates.get(to);

        Double exchangeRate = toRate / fromRate;

        return amount * exchangeRate;
    }
}
