package com.melita.weborder.config.security.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;

class WebOrderAccessDeniedHandlerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private WebOrderAccessDeniedHandler webOrderAccessDeniedHandler = new WebOrderAccessDeniedHandler();

    @Test
    void testSerializedErrorResponseWith_AuthenticationFailed() throws IOException, ServletException {
	request = new MockHttpServletRequest();
	response = new MockHttpServletResponse();
	webOrderAccessDeniedHandler.handle(request, response, new AccessDeniedException("forbidden"));
	Assertions.assertNotNull(response.getContentAsString());
	Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }

}