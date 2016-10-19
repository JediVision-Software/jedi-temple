package com.jedivision.service;

import com.jedivision.entity.User;

import java.util.List;

public interface UserService {
    List<User> findUsersExceptCurrentUser();
    List<User> findAll();
    User findByUsername(String username);
    User save(User user);
}
