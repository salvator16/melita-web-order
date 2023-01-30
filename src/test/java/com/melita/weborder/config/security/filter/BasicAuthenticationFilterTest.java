package com.melita.weborder.config.security.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasicAuthenticationFilterTest {

    private BasicAuthenticationFilter basicAuthenticationFilter;

    @Mock
    private AuthenticationManager authenticationManager;


    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;

    private final static String BASIC_HEADER = "Basic bWVsaXRhQG1lbGl0YS5jb206MTIzNDU2";

    @BeforeEach
    void setUp() {
	basicAuthenticationFilter = new BasicAuthenticationFilter(authenticationManager);
	request = new MockHttpServletRequest();
	response = new MockHttpServletResponse();
	chain = new MockFilterChain();

	request.addHeader("Authorization", BASIC_HEADER);
	request.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void whenBasicAuthHeaderIsValid_AuthenticationSuccessful() throws ServletException, IOException {
	basicAuthenticationFilter.doFilterInternal(request, response, chain);
	verify(authenticationManager, times(1)).authenticate(any());
    }




}