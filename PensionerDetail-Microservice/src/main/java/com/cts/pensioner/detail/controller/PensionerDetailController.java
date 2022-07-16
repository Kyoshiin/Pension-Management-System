package com.cts.pensioner.detail.controller;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.exception.AuthorizationException;
import com.cts.pensioner.detail.feignclient.AuthorisingClient;
import com.cts.pensioner.detail.model.PensionerDetail;
import com.cts.pensioner.detail.service.PensionerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return pensionerDetailService.getPensionerDetailByAadharCard(aadharNumber);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }

    @GetMapping("/getAllPensioner")
    public List<PensionerDetail> getAllPensioner(
            @RequestHeader(value = "Authorization") String requestTokenHeader) throws AuthorizationException {
        if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
            return pensionerDetailService.getAllPensioner();
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }
}
