package com.jedivision.services.impl;

import com.jedivision.entity.User;
import com.jedivision.repository.UserRepository;
import com.jedivision.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private static final Random RND = new Random();

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @HystrixCommand
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @HystrixCommand
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    @HystrixCommand
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @HystrixCommand
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    @HystrixCommand
    public String throwException() {
        if (RND.nextInt(2) == 1) {
            return "Service didn't throw exception";
        } else {
            throw new RuntimeException();
        }
    }
}
