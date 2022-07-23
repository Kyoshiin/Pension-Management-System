package com.cts.pensioner.detail.service;

import com.cts.pensioner.detail.exception.AadharNumberNotFound;
import com.cts.pensioner.detail.feignclient.AuthorisingClient;
import com.cts.pensioner.detail.model.PensionerDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PensionerDetailServiceTest {

    @InjectMocks
    private PensionerDetailService pensionerDetailService;

    @Mock
    private AuthorisingClient authClient;

    @Test
    public void testGetPensionDetailByAadharCard() throws AadharNumberNotFound {
        PensionerDetail pensionerDetail = new PensionerDetail(420559429029L, "Pratyay", LocalDate.of(1999, 4, 28), "BSDPS1495K", 29000, 1200, "self", "SBI", "9029486523", "private");

        String token = "dummy";
        Mockito.when(authClient.authorizeTheRequest(token)).thenReturn(true);
        assertEquals(pensionerDetailService.getPensionerDetailByAadhaarNumber(String.valueOf(420559429029l)), pensionerDetail);
    }

}
