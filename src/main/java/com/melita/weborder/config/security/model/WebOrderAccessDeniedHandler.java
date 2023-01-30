package com.melita.weborder.config.security.model;

import com.melita.weborder.util.AuthConstants;
import com.melita.weborder.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebOrderAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
	AuthUtil.serializeErrorResponse(response,
			AuthConstants.AUTH_ERROR,
			HttpStatus.FORBIDDEN);
    }

}
