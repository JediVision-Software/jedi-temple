package com.jedivision;

import com.jedivision.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationStartup {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        LOGGER.info("Delete all users from Mongodb");
        userRepository.deleteAll();
    }
}
