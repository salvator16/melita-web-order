package com.melita.weborder.config.security;

import com.melita.weborder.config.security.filter.BasicAuthenticationFilter;

import com.melita.weborder.config.security.model.WebOrderAccessDeniedHandler;
import com.melita.weborder.config.security.providers.BasicAuthenticationProvider;
import com.melita.weborder.util.AuthConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebOrderSecurityConfig {

    private final WebOrderAccessDeniedHandler webOrderAccessDeniedHandler;

    public WebOrderSecurityConfig(WebOrderAccessDeniedHandler webOrderAccessDeniedHandler) {
	this.webOrderAccessDeniedHandler = webOrderAccessDeniedHandler;
    }

    @Configuration
    @Order(1)
    public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity
			    .antMatcher("/api/**")
			    .authorizeRequests()
			    .antMatchers(AuthConstants.HEALTH_CHECK_URL).permitAll().and()
			    .exceptionHandling().accessDeniedHandler(webOrderAccessDeniedHandler).and()
			    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			    .addFilterBefore(
					    new BasicAuthenticationFilter(authenticationManager()),
					    AnonymousAuthenticationFilter.class)
			    .cors().and()
			    .csrf().disable();
	}
    }

    // We may register more authentication provider in here, Json Web Token, Api Key Authentication, OAuth2 ie.
    @Bean
    public AuthenticationManager authenticationManager(final BasicAuthenticationProvider basicAuthenticationProvider) {
	return new ProviderManager(List.of(basicAuthenticationProvider));
    }

}
