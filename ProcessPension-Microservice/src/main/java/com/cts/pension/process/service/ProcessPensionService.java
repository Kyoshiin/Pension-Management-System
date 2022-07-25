package com.cts.pension.process.service;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.exception.PensionerDetailException;
import com.cts.pension.process.feignclient.PensionDisbursementClient;
import com.cts.pension.process.feignclient.PensionerDetailClient;
import com.cts.pension.process.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessPensionService {

    @Autowired
    private PensionerDetailClient pensionerDetailClient;

    @Autowired
    private PensionDisbursementClient pensionDisbursementClient;

    /**
     * Consumes PensionDetails MicroService
     */
    public PensionDetail calculatePension(String token, PensionerInput pensionerInput) throws PensionerDetailException, AuthorizationException, AadharNumberNotFound {

        PensionerDetail pensionerDetail;

        //getting pensionerDetail via other micorservice
        try {
            pensionerDetail = pensionerDetailClient.getPensionerDetailByAadhaar(token, pensionerInput.getAadharNumber());
        } catch (AadharNumberNotFound e) {
            throw new AadharNumberNotFound("Aadhaar Card Number is not Valid. Please check it and try again");
        }


        if (pensionerInput.getAadharNumber() == pensionerDetail.getAadharNumber()
                && pensionerInput.getName().equalsIgnoreCase(pensionerDetail.getName())
                && pensionerInput.getPan().equalsIgnoreCase(pensionerDetail.getPan())) {

            double salary = pensionerDetail.getSalaryEarned();
            double allowances = pensionerDetail.getAllowances();
            double pensionAmount = 0;
            if (pensionerInput.getPensionType().equalsIgnoreCase("self")) {
                pensionAmount = 0.8 * salary + allowances;
            } else if (pensionerInput.getPensionType().equalsIgnoreCase("family")) {
                pensionAmount = 0.5 * salary + allowances;
            }

            return new PensionDetail(
                    pensionerDetail.getName(),
                    pensionerDetail.getDateOfBirth(),
                    pensionerDetail.getPan(),
                    pensionerDetail.getPensionType(),
                    pensionAmount);
        } else {
            throw new PensionerDetailException("Invalid pensioner detail provided, please provide valid detail.");
        }
    }

    /**
     * Consumes PensionDisbursement MicroService
     */
    //get the Process Response Code(10 or 21) If Process code is 10 then Suceess and 21 means not success
    public ProcessPensionResponse getCode(String token, ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound {
        try {
            //calling disbursement service for checking the details
            return pensionDisbursementClient.getResponse(token, processPensionInput);
        } catch (AadharNumberNotFound e) {
            throw new AadharNumberNotFound("Aadhaar Card Number is not Valid. Please check it and try again");
        }
    }
}
