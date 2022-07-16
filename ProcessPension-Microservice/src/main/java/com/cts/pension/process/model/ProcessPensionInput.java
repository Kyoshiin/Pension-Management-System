package com.cts.pension.process.model;

public class ProcessPensionInput {

    private long aadharNumber;
    private double pensionAmount;
    private double bankCharge; // to help in transfer values

    public ProcessPensionInput() {
    }

    public ProcessPensionInput(long aadharNumber, double pensionAmount, double bankCharge) {
        this.aadharNumber = aadharNumber;
        this.pensionAmount = pensionAmount;
        this.bankCharge = bankCharge;
    }

    public long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(long aadharNumber) {
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
