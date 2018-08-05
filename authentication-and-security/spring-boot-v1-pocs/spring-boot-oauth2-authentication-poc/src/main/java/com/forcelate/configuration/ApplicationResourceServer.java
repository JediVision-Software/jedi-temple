package com.forcelate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ApplicationResourceServer extends ResourceServerConfigurerAdapter {
	private ApplicationProperties applicationProperties;

	@Autowired
	public ApplicationResourceServer(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources
				.resourceId(applicationProperties.getAppConfigs().getOauth().getResourceId())
				.stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.anonymous().disable()
				.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and()
				.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
