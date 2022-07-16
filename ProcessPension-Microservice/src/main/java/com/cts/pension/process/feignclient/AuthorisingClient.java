package com.cts.pension.process.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorizatiion-Microservice", url = "http://localhost:8400/auth")
public interface AuthorisingClient {

    @PostMapping("/validate")
    boolean authorizeTheRequest(@RequestHeader(value = "Authorization") String requestTokenHeader);
}
