package com.cts.pensioner.detail.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "pensioner")
public class PensionerDetail {

    @Id
    @Column(name = "aadhar")
    private long aadharNumber;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private LocalDate dateOfBirth;
    private String pan;
    @Column(name = "salary")
    private double salaryEarned;
    private double allowances;
    @Column(name = "pension")
    private String pensionType;
    @Column(name = "bank")
    private String bankName;
    @Column(name = "accnumber")
    private String accountNumber;
    @Column(name = "banktype")
    private String bankType;

    public PensionerDetail(long aadharNumber, String name, LocalDate dateOfBirth, String pan, double salaryEarned, double allowances, String pensionType, String bankName, String accountNumber, String bankType) {
        this.aadharNumber = aadharNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.pan = pan;
        this.salaryEarned = salaryEarned;
        this.allowances = allowances;
        this.pensionType = pensionType;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.bankType = bankType;
    }

    public PensionerDetail() {

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

    public double getSalaryEarned() {
        return salaryEarned;
    }

    public void setSalaryEarned(double salaryEarned) {
        this.salaryEarned = salaryEarned;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
}
