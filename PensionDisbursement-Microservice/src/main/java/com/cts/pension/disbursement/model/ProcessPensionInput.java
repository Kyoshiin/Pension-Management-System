package com.cts.pension.disbursement.model;

public class ProcessPensionInput {
    private Long aadharNumber;
    private double pensionAmount;
    private double bankCharge;

    public ProcessPensionInput() {
    }

    public ProcessPensionInput(Long aadharNumber, double pensionAmount, double bankCharge) {
        this.aadharNumber = aadharNumber;
        this.pensionAmount = pensionAmount;
        this.bankCharge = bankCharge;
    }

    public Long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(Long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public double getPensionAmount() {
        return pensionAmount;
    }

    public void setPensionAmount(double pensionAmount) {
        this.pensionAmount = pensionAmount;
    }

    public double getBankCharge() {
        return bankCharge;
    }

    public void setBankCharge(double bankCharge) {
        this.bankCharge = bankCharge;
    }

}