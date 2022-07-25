package com.cts.pension.process.controller;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.exception.PensionerDetailException;
import com.cts.pension.process.feignclient.AuthorisingClient;
import com.cts.pension.process.model.PensionDetail;
import com.cts.pension.process.model.PensionerInput;
import com.cts.pension.process.model.ProcessPensionInput;
import com.cts.pension.process.model.ProcessPensionResponse;
import com.cts.pension.process.service.ProcessPensionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProcessPensionControllerMethodTest {


    @InjectMocks
    private ProcessPensionController processPensionController;

    @MockBean
    private AuthorisingClient authorisingClient;

    @Mock
    private ProcessPensionService processPensionService;


    @Test
    public void testControllerObjectNotNull() {
        assertNotNull(processPensionController);
    }

    @Test
    public void testServiceObjectNotNull() {
        assertNotNull(processPensionService);
    }


    @Test
    public void testGetPensionDetail() throws PensionerDetailException, AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        Mockito.when(authorisingClient.authorizeTheRequest(token)).thenReturn(true);
        PensionerInput pensionerInput = new PensionerInput(420559429029l, "Parthik", LocalDate.of(1999, 12, 03), "BSDPS1495K", "self");
        PensionDetail pensionDetail = new PensionDetail("Parthik", LocalDate.of(1999, 12, 03), "BSDPS1495K", "self", 24560.0);
        Mockito.when(processPensionService.calculatePension(token, pensionerInput)).thenReturn(pensionDetail);
        assertEquals(processPensionController.getPensionDetail(token, pensionerInput), pensionDetail);
    }


    @Test
    public void testGetprocessingCode10() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        Mockito.when(authorisingClient.authorizeTheRequest(token)).thenReturn(true);
        ProcessPensionInput processPensionInput = new ProcessPensionInput(420559429029l, 24400.0, 550);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(10);

        Mockito.when(processPensionService.getCode(token, processPensionInput)).thenReturn(processPensionResponse);
        assertEquals(processPensionController.getprocessingCode(token, processPensionInput), processPensionResponse);
    }

    @Test
    public void testGetprocessingCode21() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        Mockito.when(authorisingClient.authorizeTheRequest(token)).thenReturn(true);
        ProcessPensionInput processPensionInput = new ProcessPensionInput(420559429029l, 24400.0, 500);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(21);

        Mockito.when(processPensionService.getCode(token, processPensionInput)).thenReturn(processPensionResponse);
        assertEquals(processPensionController.getprocessingCode(token, processPensionInput), processPensionResponse);
    }

}

