package com.melita.weborder.config.security.filter;

import com.melita.weborder.config.security.model.BasicAuthDetails;
import com.melita.weborder.exception.CommonAuthException;
import com.melita.weborder.util.AuthConstants;
import com.melita.weborder.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class BasicAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    public BasicAuthenticationFilter(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	try {
	    Optional<String> header = Optional.ofNullable(request.getHeader(AuthConstants.AUTHORIZATION));
	    if (header.isEmpty())
		throw new CommonAuthException("Authorization header is missing", HttpStatus.UNAUTHORIZED);

	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(null,
			    new BasicAuthDetails(header.get(), request.getRemoteAddr()));

	    authenticationHandler(token);

	    filterChain.doFilter(request, response);
	} catch (CommonAuthException e) {
	    AuthUtil.serializeErrorResponse(response,
			    e.getMessage(),
			    e.getStatus());
	}
    }

    private void authenticationHandler(AbstractAuthenticationToken authenticationToken) {
	SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	return AuthConstants.HEALTH_CHECK_URL.equals(request.getRequestURI());
    }

}
