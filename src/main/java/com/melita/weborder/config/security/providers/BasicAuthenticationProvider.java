package com.melita.weborder.config.security.providers;

import com.melita.weborder.config.security.converter.UserConverter;
import com.melita.weborder.config.security.model.BasicAuthDetails;
import com.melita.weborder.domain.repositories.OrderUserRepository;
import com.melita.weborder.exception.CommonAuthException;
import com.melita.weborder.model.UserResource;
import com.melita.weborder.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final OrderUserRepository orderUserRepository;
    private final UserConverter userConverter;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

	if (ObjectUtils.isEmpty(authentication.getCredentials()))
	    return new UsernamePasswordAuthenticationToken(null, null, null);

	BasicAuthDetails basicAuthDetails = (BasicAuthDetails) authentication.getCredentials();
	String[] credentials = AuthUtil.parseCredentials(basicAuthDetails.header());

	UserResource userResource = verifyAndGetUser(credentials[0], credentials[1]);

	return new UsernamePasswordAuthenticationToken(userResource, null, null);

    }

    private UserResource verifyAndGetUser(String username, String password) {
	return orderUserRepository.findByEmailAndPassword(username, password)
			.map(userConverter::convert)
			.orElseThrow(CommonAuthException::new);
    }

    @Override
    public boolean supports(Class<?> authentication) {
	return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
