package com.cts.pension.process.service;

import com.cts.pension.process.exception.AadharNumberNotFound;
import com.cts.pension.process.exception.AuthorizationException;
import com.cts.pension.process.feignclient.PensionDisbursementClient;
import com.cts.pension.process.feignclient.PensionerDetailClient;
import com.cts.pension.process.model.ProcessPensionInput;
import com.cts.pension.process.model.ProcessPensionResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProcessPensionServiceTest {

    @InjectMocks
    private ProcessPensionService processPensionService;

    @Mock
    private PensionerDetailClient pensionerDeatailFeignClient;

    @Mock
    private PensionDisbursementClient pensionDisbursementClient;


//	@Test
//	public void testCalculatePension() throws PensionerDetailException, AuthorizationException, AadharNumberNotFound
//	{
//		String token = "dummy";
//		PensionerInput pensionerInput = new PensionerInput(420559429029L, "Pratyay", LocalDate.of(1999, 4,28), "BSDPS1495K", "self");
//		PensionDetail pensionDetail =new PensionDetail("Pratyay", LocalDate.of(1999, 4,28), "BSDPS1495K", "self", 24400.0);
//		PensionerDetail pensionerDetail = new PensionerDetail(420559429029L, "Pratyay", LocalDate.of(1999, 4,28), "BSDPS1495K", 29000, 1200, "self", "SBI", "9029486523", "private");
//		Mockito.when(pensionerDeatailFeignClient.getPensionerDetailByAadhaar(token, 420559429029L)).thenReturn(pensionerDetail);
//		assertEquals(processPensionService.calculatePension(token, pensionerInput), pensionDetail);
//	}

    @Test
    public void testGetCodePrivate21() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        ProcessPensionInput processPensionInput = new ProcessPensionInput(420559429029l, 24400.0, 500);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(21);
        Mockito.when(pensionDisbursementClient.getResponse(token, processPensionInput)).thenReturn(processPensionResponse);

        assertEquals(processPensionService.getCode(token, processPensionInput), processPensionResponse);
    }

    @Test
    public void testGetCodePrivate10() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        ProcessPensionInput processPensionInput = new ProcessPensionInput(420559429029l, 24400.0, 550);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(10);
        Mockito.when(pensionDisbursementClient.getResponse(token, processPensionInput)).thenReturn(processPensionResponse);

        assertEquals(processPensionService.getCode(token, processPensionInput), processPensionResponse);
    }

    @Test
    public void testGetCodePublic21() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        ProcessPensionInput processPensionInput = new ProcessPensionInput(342567345637l, 32002.0, 500);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(21);
        Mockito.when(pensionDisbursementClient.getResponse(token, processPensionInput)).thenReturn(processPensionResponse);

        assertEquals(processPensionService.getCode(token, processPensionInput), processPensionResponse);
    }

    @Test
    public void testGetCodePublic10() throws AuthorizationException, AadharNumberNotFound {
        String token = "dummy";
        ProcessPensionInput processPensionInput = new ProcessPensionInput(342567345637l, 32002.0, 550);
        ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
        processPensionResponse.setProcessPensionStatusCode(10);
        Mockito.when(pensionDisbursementClient.getResponse(token, processPensionInput)).thenReturn(processPensionResponse);

        assertEquals(processPensionService.getCode(token, processPensionInput), processPensionResponse);
    }
}
