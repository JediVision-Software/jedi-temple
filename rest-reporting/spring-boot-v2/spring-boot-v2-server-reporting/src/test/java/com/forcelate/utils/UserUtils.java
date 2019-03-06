package com.forcelate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forcelate.entity.User;
import lombok.NoArgsConstructor;

import java.io.IOException;


@NoArgsConstructor
public final class UserUtils {

    private ObjectMapper objectMapper = new ObjectMapper();

    public String createUser() throws IOException {
        User user = new User();
        user.setId(3L);
        user.setUsername("username3");
        user.setAge(28);

        return objectMapper.writeValueAsString(user);
    }
}
