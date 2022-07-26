package com.cts.pension.disbursement.service;

import com.cts.pension.disbursement.exception.AadharNumberNotFound;
import com.cts.pension.disbursement.exception.AuthorizationException;
import com.cts.pension.disbursement.feignclient.PensionDetailClient;
import com.cts.pension.disbursement.model.PensionerDetail;
import com.cts.pension.disbursement.model.ProcessPensionInput;
import com.cts.pension.disbursement.model.ProcessPensionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PensionDisbursementService {

    @Autowired
    private PensionDetailClient pensionDetailClient;

    public ProcessPensionResponse getResponse(String token, ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound {
        PensionerDetail pensionerDetail;

        //getting details from PensionDetail microservice
        try {
            pensionerDetail = pensionDetailClient.getPensionerDetailByAadhaar(token, processPensionInput.getAadharNumber());
        } catch (AadharNumberNotFound e) {
            throw new AadharNumberNotFound("Aadhaar Number Not found");
        }

        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        double serviceCharge = processPensionInput.getBankCharge();

        //calc service charge
        double checkAmount = 0;
        if (pensionerDetail.getBankType().equalsIgnoreCase("public")) {
            checkAmount = 500;
        } else if (pensionerDetail.getBankType().equalsIgnoreCase("private")) {
            checkAmount = 550;
        }

        //checking if charge is correct
        if (checkAmount == serviceCharge && verifyPensionAmount(pensionerDetail, processPensionInput.getPensionAmount())) {
            processPensionResponse.setProcessPensionStatusCode(10);
        } else {
            processPensionResponse.setProcessPensionStatusCode(21);
        }

        return processPensionResponse;
    }

    public boolean verifyPensionAmount(PensionerDetail pensionerDetails, double pensionAmount) {

        double expectedAmount = 0;

        // calculating Pension Amount
        if (pensionerDetails.getPensionType().equalsIgnoreCase("Self")) {
            expectedAmount = (0.80 * pensionerDetails.getSalaryEarned()) + pensionerDetails.getAllowances();
        } else if (pensionerDetails.getPensionType().equalsIgnoreCase("Family")) {
            expectedAmount = (0.50 * pensionerDetails.getSalaryEarned()) + pensionerDetails.getAllowances();
        }

        return expectedAmount == pensionAmount;
    }

}
