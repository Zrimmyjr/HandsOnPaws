package com.zrimmyjr.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
       name =  "fraudster" ,
        url = "${clients.fraudster.url}"
)
public interface FraudClient {
    @GetMapping(path = "api/v1/fraudster-check/{customerId}")
    FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId);


}
