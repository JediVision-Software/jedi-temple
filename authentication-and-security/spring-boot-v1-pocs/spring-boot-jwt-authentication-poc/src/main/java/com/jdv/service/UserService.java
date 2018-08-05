package com.jdv.service;

import com.jdv.domain.ApplicationUser;

public interface UserService {
	ApplicationUser getUser(String userId);
	String saveUser(ApplicationUser user);
}