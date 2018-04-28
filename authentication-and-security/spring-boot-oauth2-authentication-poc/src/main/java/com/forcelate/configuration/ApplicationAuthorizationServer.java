package com.forcelate.configuration;

import com.forcelate.properties.nested.OAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class ApplicationAuthorizationServer extends AuthorizationServerConfigurerAdapter {
	private TokenStore tokenStore;
	private AuthenticationManager authenticationManager;
	private ApplicationProperties applicationProperties;

	@Autowired
	public ApplicationAuthorizationServer(AuthenticationManager authenticationManager,
										  ApplicationProperties applicationProperties) {
		this.tokenStore = new InMemoryTokenStore();
		this.authenticationManager = authenticationManager;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		OAuth oauth = applicationProperties.getAppConfigs().getOauth();
		configurer
				.inMemory()
				.withClient(oauth.getClientId())
				.secret(oauth.getClientSecret())
				.authorizedGrantTypes(oauth.getGrandTypes())
				.scopes(oauth.getScopes())
				.accessTokenValiditySeconds(oauth.getAccessTokenValiditySeconds())
				.refreshTokenValiditySeconds(oauth.getRefreshTokenValiditySeconds());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager);
	}
}
