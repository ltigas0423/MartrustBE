package com.example.martrustbe.resource;

import com.example.martrustbe.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/exchange-rate")
public class ExchangeRateResource {

    @Autowired
    private ExchangeRateService exchangeRateService;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Double>> getExchangeRates() {
        return ResponseEntity.ok(exchangeRateService.getExchangeRates());
    }


    @GetMapping
    public ResponseEntity<Double> getExchangeRate(@RequestParam("from") String from, @RequestParam("to") String to) {
        return ResponseEntity.ok(exchangeRateService.getExchangeRate(from, to));
    }

    @PostMapping("/convert")
    public ResponseEntity<Double> convert(@RequestParam("from") String symbol,
                                          @RequestParam("to") String to,
                                          @RequestParam("amount") Double amount) {
        return ResponseEntity.ok(exchangeRateService.convert(symbol, to, amount));
    }
}
