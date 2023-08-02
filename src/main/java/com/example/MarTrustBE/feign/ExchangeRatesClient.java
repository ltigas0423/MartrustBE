package com.example.MarTrustBE.feign;

import com.example.MarTrustBE.data.ExchangeRateConvertResponse;
import com.example.MarTrustBE.data.ExchangeRateLatestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name ="ExchangeRatesClient",
        url = "http://api.exchangeratesapi.io/v1/"
)
public interface ExchangeRatesClient {

    @GetMapping("/latest")
    ExchangeRateLatestResponse getLatest(@RequestParam(name = "access_key") String accessKey);

    @GetMapping("/convert")
    ExchangeRateConvertResponse convert(@RequestParam(name = "access_key") String accessKey,
                                        @RequestParam(name = "from") String from,
                                        @RequestParam(name = "to") String to,
                                        @RequestParam(name = "amount") Double amount);


}
