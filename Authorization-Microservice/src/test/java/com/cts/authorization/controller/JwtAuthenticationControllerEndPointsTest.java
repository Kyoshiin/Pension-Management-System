package com.cts.authorization.controller;

import com.cts.authorization.config.JwtTokenUtil;
import com.cts.authorization.model.AuthenticationRequest;
import com.cts.authorization.model.UserModel;
import com.cts.authorization.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerEndPointsTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtUserDetailsService userDetailsService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private AuthenticationManager authenticationManager;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBadRequestGenerateToken() throws Exception {
        this.mockMvc.perform(post("/authenticate")).andExpect(status().isBadRequest());
    }

    @Test
    void testAuthorizedGenerateToken() throws Exception {

        UserModel user = new UserModel(1, "admin", "admin");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), new ArrayList<>());
        when(jwtTokenUtil.generateToken(details)).thenReturn("Bearer @token@token");
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(new AuthenticationRequest("admin", "admin"));
        this.mockMvc.perform(post("/api/v1/authenticate").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testBadRequest() throws Exception {
        this.mockMvc.perform(post("/authenticate")).andExpect(status().isBadRequest());
    }

    @Test
    void testRandomUrl() throws Exception {
        this.mockMvc.perform(get("/api/v1/other/url")).andExpect(status().isNotFound());
    }

    @Test
    void textExistingUserAuthenticate() throws Exception {
        UserModel user = new UserModel(1, "admin", "pass");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), new ArrayList<>());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                "admin", "admin");
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin")))
                .thenReturn(usernamePasswordAuthenticationToken);
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
        when(jwtTokenUtil.generateToken(details)).thenReturn("token");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new AuthenticationRequest("admin", "pass")))).andExpect(status().isUnauthorized());

    }

    @Test
    void textExistingUserAuthorize() throws Exception {
        UserModel user = new UserModel(1, "admin", "pass");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), new ArrayList<>());
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
        mockMvc.perform(MockMvcRequestBuilders.post("/authorize").header("Authorization", "Bearer token"))
                .andExpect(status().isOk());

    }

    @Test
    void textNullTokenAuthorize() throws Exception {
        UserModel user = new UserModel(1, "admin", "pass");
        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), new ArrayList<>());
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
        when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
        mockMvc.perform(MockMvcRequestBuilders.post("/validate").header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }


}
