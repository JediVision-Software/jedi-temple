package com.jdv.service.impl;

import com.jdv.domain.ApplicationUser;
import com.jdv.service.TokenService;
import com.jdv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	private TokenService tokenService;
	// users in memory
	private static Map<String, ApplicationUser> users = new HashMap<>();

	@Autowired
	public UserServiceImpl(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public ApplicationUser getUser(String userId) {
		return users.get(userId);
	}

	@Override
	public String saveUser(ApplicationUser user) {
		String id = UUID.randomUUID().toString();
		user.setId(id);
		users.put(id, user);
		return tokenService.createToken(id);
	}
}
