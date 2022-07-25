package com.cts.pension.process.feignclient;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.model.PensionerDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PensionerDetail-Microservice", url = "${pensionDetail.url}")
public interface PensionerDetailClient {

    @GetMapping("/PensionerDetailByAadhaar/{aadharNumber}")
    PensionerDetail getPensionerDetailByAadhaar(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @PathVariable long aadharNumber) throws AuthorizationException, AadharNumberNotFound;


}
