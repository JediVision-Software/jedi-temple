package com.jdv.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jdv.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
	private static final String TOKEN_SECRET = "ForceElate2018";
	private static final String USER_ID = "userId";
	private static final String CREATED_AT = "createdAt";

	@Override
	public String createToken(String userId) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
			return JWT.create()
					.withClaim(USER_ID, userId)
					.withClaim(CREATED_AT, new Date())
					.sign(algorithm);
		} catch (UnsupportedEncodingException | JWTCreationException e) {
			LOGGER.error("Error during creation security token", e);
		}
		return null;
	}

	@Override
	public String getUserIdFromToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim(USER_ID).asString();
		} catch (UnsupportedEncodingException | JWTVerificationException e) {
			LOGGER.error("Error during parsing security token", e);
			return null;
		}
	}

	@Override
	public boolean isTokenValid(String token) {
		String userId = this.getUserIdFromToken(token);
		return nonNull(userId);
	}
}
