package com.cts.pensioner.detail.service;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.model.PensionerDetail;
import com.cts.pensioner.detail.repository.PensionerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PensionerDetailService {

    @Autowired
    private PensionerDetailRepository pensionerDetailRepository;


    /**
     * Loads pensioner details from sql file
     */
    public PensionerDetail getPensionerDetailByAadharCardFromDB(long aadharNumber) throws AadharNumberNotFound {
        return pensionerDetailRepository.findById(aadharNumber).orElseThrow(() ->
                new AadharNumberNotFound("Aadhaar Card Number is not Valid. Please check it and try again"));
    }

    /**
     * Loads pensioner details from flat(csv) file
     */
    public PensionerDetail getPensionerDetailByAadhaarNumberFromCSV(String aadhaarNumber) throws AadharNumberNotFound {

        String line;
        BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/details.csv")));
        try {
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                // convert record into strings
                String[] pensioner = line.split(",");
                // if Aadhaar number is found, then return the details
                if (aadhaarNumber.contentEquals(pensioner[0])) {
                    System.out.println("Details found");

                    PensionerDetail pensionerDetail = new PensionerDetail();
                    pensionerDetail.setAadharNumber(Long.parseLong(pensioner[0]));
                    pensionerDetail.setName(pensioner[1]);
                    pensionerDetail.setDateOfBirth(parseDate(pensioner[2]));
                    pensionerDetail.setPan(pensioner[3]);
                    pensionerDetail.setSalaryEarned(Double.parseDouble(pensioner[4]));
                    pensionerDetail.setAllowances(Double.parseDouble(pensioner[5]));
                    pensionerDetail.setPensionType(pensioner[6]);
                    pensionerDetail.setBankName(pensioner[7]);
                    pensionerDetail.setAccountNumber(pensioner[8]);
                    pensionerDetail.setBankType(pensioner[9]);
                    return pensionerDetail;
                }
            }
        } catch (NumberFormatException | IOException | ParseException e) {
            throw new AadharNumberNotFound("No such Aadhar number found");
        }
        throw new AadharNumberNotFound("No such Aadhar number found");
    }

    public LocalDate parseDate(String inpdate) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(inpdate);
        return date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate();
    }

}
