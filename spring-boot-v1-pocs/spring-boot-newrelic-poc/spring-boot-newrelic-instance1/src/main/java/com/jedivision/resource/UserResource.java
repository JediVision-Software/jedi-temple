package com.jedivision.resource;

import com.jedivision.entity.User;
import com.jedivision.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.jedivision.utils.RandomUtils.randomUser;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public User save() {
        LOGGER.info("Instance #1: save random user...");
        return userRepository.save(randomUser());
    }

    @RequestMapping(value = "/bulk/{bulkSize}", method = RequestMethod.POST)
    public List<User> bulkSave(@PathVariable(value = "bulkSize") int bulkSize) {
        LOGGER.info("Instance #1: save {} random users...", bulkSize);
        return userRepository.save(IntStream.range(0, bulkSize)
                .mapToObj(i -> randomUser())
                .collect(Collectors.toList()));
    }
}
