package com.cts.pension.process.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Class for pension type Details
 */
public class PensionDetail {


    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String pan;
    private String pensionType;
    private double pensionAmount;

    public PensionDetail() {
    }

    public PensionDetail(String name, LocalDate dateOfBirth, String pan, String pensionType, double pensionAmount) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.pan = pan;
        this.pensionType = pensionType;
        this.pensionAmount = pensionAmount;
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

    public double getPensionAmount() {
        return pensionAmount;
    }

    public void setPensionAmount(double pensionAmount) {
        this.pensionAmount = pensionAmount;
    }
}
