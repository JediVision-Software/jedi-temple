package com.forcelate.utils;

import com.forcelate.entity.User;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public final class UserUtils {

    private List<User>users = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        this.users.add(User.builder()
                .id(1L)
                .username("username1")
                .age(14)
                .build());
        this.users.add(User.builder()
                .id(2L)
                .username("username2")
                .age(25)
                .build());
    }

    public void add() {

        this.users.add(User.builder()
                .id(3L)
                .age(33)
                .username("username3")
                .build()) ;

    }

    public List<User> findAll() {
        return this.users;
    }

    public User findOne(Long userId) {
        return this.users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
