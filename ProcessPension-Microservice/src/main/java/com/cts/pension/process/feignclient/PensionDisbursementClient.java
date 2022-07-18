package com.cts.pension.process.feignclient;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.model.ProcessPensionInput;
import com.cts.pension.process.model.ProcessPensionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "PensionDisbursement-Microservices", url = "${pensionDisbursement.url}")
public interface PensionDisbursementClient {

    @PostMapping("/disbursePension")
    ProcessPensionResponse getResponse(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @RequestBody ProcessPensionInput processpensionInput)
            throws AuthorizationException, AadharNumberNotFound;

}
