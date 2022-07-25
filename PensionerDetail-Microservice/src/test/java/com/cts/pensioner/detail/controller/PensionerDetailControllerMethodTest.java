package com.cts.pensioner.detail.controller;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.exception.AuthorizationException;
import com.cts.pensioner.detail.feignclient.AuthorisingClient;
import com.cts.pensioner.detail.model.PensionerDetail;
import com.cts.pensioner.detail.service.PensionerDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PensionerDetailControllerMethodTest {

    @InjectMocks
    private PensionerDetailController pensionerDetailController;

    @MockBean
    private AuthorisingClient authorisingClient;

    @Mock
    private PensionerDetailService pensionerDetailService;

    @Test
    public void testToken() {
        String token = "dummy";
        Mockito.when(authorisingClient.authorizeTheRequest(token)).thenReturn(true);
    }

    @Test
    public void testGetPensionerDetailByAadhar() throws AadharNumberNotFound, AuthorizationException {
        String token = "dummy";
        Mockito.when(authorisingClient.authorizeTheRequest(token)).thenReturn(true);
        PensionerDetail pensionerDetail = new PensionerDetail(420559429029L, "Pratyay", LocalDate.of(1999, 4, 28), "BSDPS1495K", 29000, 1200, "self", "SBI", "9029486523", "private");
        Mockito.when(pensionerDetailService.getPensionerDetailByAadhaarNumber(String.valueOf(420559429029L))).thenReturn(pensionerDetail);
        assertEquals(pensionerDetailController.getPensionerDetailByAadhar(token, 420559429029L), pensionerDetail);
    }

}
