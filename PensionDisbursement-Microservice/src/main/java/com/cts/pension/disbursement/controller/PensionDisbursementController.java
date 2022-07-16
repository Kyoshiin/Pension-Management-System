package com.cts.pension.disbursement.controller;

import com.cts.pension.disbursement.exception.AadharNumberNotFound;
import com.cts.pension.disbursement.exception.AuthorizationException;
import com.cts.pension.disbursement.feignclient.AuthorisingClient;
import com.cts.pension.disbursement.model.ProcessPensionInput;
import com.cts.pension.disbursement.model.ProcessPensionResponse;
import com.cts.pension.disbursement.service.PensionDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PensionDisbursementController {

    @Autowired
    private PensionDisbursementService pensionDisbursementService;

    @Autowired
    private AuthorisingClient authorisingClient;

    //takes input about pensioner Details
    @PostMapping("/disbursePension")
    public ProcessPensionResponse getResponse(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @RequestBody ProcessPensionInput processpensionInput) throws AuthorizationException, AadharNumberNotFound {

        if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
            return pensionDisbursementService.getResponse(requestTokenHeader, processpensionInput);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }
}
