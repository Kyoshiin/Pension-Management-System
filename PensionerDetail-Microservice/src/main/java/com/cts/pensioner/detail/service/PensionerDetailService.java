package com.cts.pensioner.detail.service;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.model.PensionerDetail;
import com.cts.pensioner.detail.repository.PensionerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PensionerDetailService {

    @Autowired
    private PensionerDetailRepository pensionerDetailRepository;

    public PensionerDetail getPensionerDetailByAadharCard(long aadharNumber) throws AadharNumberNotFound {
        return pensionerDetailRepository.findById(aadharNumber).orElseThrow(() ->
                new AadharNumberNotFound("Aadhar Card Number is not Valid. Please check it and try again"));//TODO:edit
    }

    public List<PensionerDetail> getAllPensioner(String token) {
        return pensionerDetailRepository.findAll();
    }

}
