package com.jedivision.security;

import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class H2UserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public H2UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        return new H2UserDetails(user);
    }
}
