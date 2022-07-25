package com.cts.pension.process.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * for requestBody for pensioner
 */
public class PensionerInput {

    private long aadharNumber;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String pan;
    private String pensionType;

    public PensionerInput() {
    }

    public PensionerInput(long aadharNumber, String name, LocalDate dateOfBirth, String pan, String pensionType) {
        this.aadharNumber = aadharNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.pan = pan;
        this.pensionType = pensionType;
    }

    public long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }
}
