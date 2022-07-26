package com.cts.pension.process.controller;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.exception.PensionerDetailException;
import com.cts.pension.process.feignclient.AuthorisingClient;
import com.cts.pension.process.model.PensionDetail;
import com.cts.pension.process.model.PensionerInput;
import com.cts.pension.process.model.ProcessPensionInput;
import com.cts.pension.process.model.ProcessPensionResponse;
import com.cts.pension.process.service.ProcessPensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProcessPensionController {

    @Autowired
    ProcessPensionService processPensionService;

    @Autowired
    private AuthorisingClient authorisingClient;

    //for calculating pension
    @PostMapping("/PensionDetail")
    public PensionDetail getPensionDetail(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @RequestBody PensionerInput pensionerInput)
            throws AuthorizationException, PensionerDetailException, AadharNumberNotFound {

        if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {

            return processPensionService.calculatePension(requestTokenHeader, pensionerInput);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }

    //for status code response
    //Returns the Process Response Code(10 or 21) If Process code is 10 then Success and 21 means not success
    @PostMapping("/ProcessPension")
    public ProcessPensionResponse getprocessingCode(
            @RequestHeader(value = "Authorization") String requestTokenHeader,
            @RequestBody ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound {

        //check authorization
        if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {

            //return the process code
            return processPensionService.getCode(requestTokenHeader, processPensionInput);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }
}
