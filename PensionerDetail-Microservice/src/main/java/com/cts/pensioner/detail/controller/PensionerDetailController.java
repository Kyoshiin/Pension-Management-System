package com.cts.pensioner.detail.controller;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.exception.AuthorizationException;
import com.cts.pensioner.detail.feignclient.AuthorisingClient;
import com.cts.pensioner.detail.model.PensionerDetail;
import com.cts.pensioner.detail.service.PensionerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PensionerDetailController {

    @Autowired
    PensionerDetailService pensionerDetailService;

    @Autowired
    private AuthorisingClient authorisingClient;

    @GetMapping("/PensionerDetailByAadhaar/{aadharNumber}")
    public PensionerDetail getPensionerDetailByAadhar(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @PathVariable long aadharNumber) throws AuthorizationException, AadharNumberNotFound {

        if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
            return pensionerDetailService.getPensionerDetailByAadhaarNumber(String.valueOf(aadharNumber));
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }
}
