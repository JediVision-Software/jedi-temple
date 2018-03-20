package com.jdv.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	private static final String REALM_NAME = "DigestRealm";
	private static final String SECRET_KEY = "SecureKey";
	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(digestAuthenticationFilter())
				.exceptionHandling()
				.authenticationEntryPoint(digestEntryPoint())
				.and()
					.httpBasic()
				.and()
					.authorizeRequests()
					.antMatchers("/api/public").permitAll()
					.antMatchers("/api/private").hasRole(USER_ROLE)
					.antMatchers("/api/onlyAdmin").hasRole(ADMIN_ROLE)
				.and()
					.csrf().disable();
	}

	private DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(userDetailsServiceBean());
		digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
		return digestAuthenticationFilter;
	}

	private DigestAuthenticationEntryPoint digestEntryPoint() {
		DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
		digestAuthenticationEntryPoint.setRealmName(REALM_NAME);
		digestAuthenticationEntryPoint.setKey(SECRET_KEY);
		return digestAuthenticationEntryPoint;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user1").password("user1").roles(USER_ROLE)
				.and()
				.withUser("user2").password("user2").roles(USER_ROLE)
				.and()
				.withUser("admin").password("admin").roles(ADMIN_ROLE, USER_ROLE);
	}
}
