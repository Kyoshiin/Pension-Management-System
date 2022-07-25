package com.cts.pension.disbursement.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorization-Microservice", url = "${auth.url}")
public interface AuthorisingClient {

    @PostMapping("/validate")
    boolean authorizeTheRequest(@RequestHeader(value = "Authorization") String requestTokenHeader);
}
