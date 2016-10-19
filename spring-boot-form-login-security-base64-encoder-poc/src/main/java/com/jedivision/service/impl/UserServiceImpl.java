package com.jedivision.service.impl;

import com.jedivision.dao.UserDao;
import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import com.jedivision.utils.Base64PasswordEncoder;
import com.jedivision.utils.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jedivision.utils.StreamUtils.stream;

@Service
public class UserServiceImpl implements UserService {
    // DAO
    private final UserDao userDao;
    // Encoder
    private final Base64PasswordEncoder encoder;
    // Utility
    private final PrincipalUtils principalUtils;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           Base64PasswordEncoder encoder,
                           PrincipalUtils principalUtils) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.principalUtils = principalUtils;
    }

    @Override
    public List<User> findUsersExceptCurrentUser() {
        Authentication authentication = principalUtils.getAuthentication();
        return userDao.findUsersExceptCurrentUser(authentication.getName());
    }

    @Override
    public List<User> findAll() {
        return stream(userDao.findAll()).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userDao.findByUsername(username));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User " + username + " not found in database");
        }
    }

    @Override
    public User save(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return userDao.save(user);
    }
}
