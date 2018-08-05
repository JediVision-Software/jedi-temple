package com.forcelate.properties.nested;

import lombok.Data;

@Data
public class OAuth {
	private String clientId;
	private String clientSecret;
	private String[] grandTypes;
	private String[] scopes;
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;
	private String resourceId;
}
