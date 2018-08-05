package com.forcelate.service;

import com.forcelate.entity.User;

import java.util.List;

public interface UserService {
	User save(User user);
	List<User> findAll();
	void delete(long id);
}
