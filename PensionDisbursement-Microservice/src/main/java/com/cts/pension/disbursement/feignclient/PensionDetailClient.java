package com.cts.pension.disbursement.feignclient;

import com.cts.pension.disbursement.exception.AadharNumberNotFound;
import com.cts.pension.disbursement.exception.AuthorizationException;
import com.cts.pension.disbursement.model.PensionerDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PensionerDetail-Microservice", url = "http://localhost:8200/pensioner")
public interface PensionDetailClient {

    @GetMapping("/PensionerDetailByAadhaar/{aadharNumber}")
    PensionerDetail getPensionerDetailByAadhaar(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @PathVariable long aadharNumber) throws AuthorizationException, AadharNumberNotFound;
}
