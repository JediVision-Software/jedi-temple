package com.jdv.configuration;

import com.jdv.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	private TokenService tokenService;

	@Autowired
	public ApplicationSecurity(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.addFilterAfter(new JWTFilter(tokenService), FilterSecurityInterceptor.class)
				.csrf().disable();
	}
}