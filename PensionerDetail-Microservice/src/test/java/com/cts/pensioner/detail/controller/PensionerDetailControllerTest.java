package com.cts.pensioner.detail.controller;

import com.cts.pensioner.detail.feignclient.AuthorisingClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class PensionerDetailControllerTest {

    @MockBean
    private AuthorisingClient authorisingClient; // = mock(AuthorisingClient.class);

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetResponse() throws Exception {
        when(authorisingClient.authorizeTheRequest("Bearer @token@token")).thenReturn(true);
        mockMvc.perform(get("/PensionerDetailByAadhaar/420559429029").header("Authorization", "Bearer @token@token"))
                .andExpect(status().isOk());
    }


    @Test
    void testGetResponseBadRequest() throws Exception {
        when(authorisingClient.authorizeTheRequest("Bearer @token@token")).thenReturn(true);
        mockMvc.perform(get("/PensionerDetailByAadhaar/420559429029")).andExpect(status().isBadRequest());
    }


}
