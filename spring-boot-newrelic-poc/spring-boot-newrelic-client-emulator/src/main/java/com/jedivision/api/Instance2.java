package com.jedivision.api;

import com.jedivision.entity.User;
import feign.RequestLine;

import java.util.List;

public interface Instance2 {
    @RequestLine("GET /api/user")
    List<User> findUsers();
}
