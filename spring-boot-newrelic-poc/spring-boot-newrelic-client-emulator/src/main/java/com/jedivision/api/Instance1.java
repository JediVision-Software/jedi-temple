package com.jedivision.api;

import com.jedivision.entity.User;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface Instance1 {
    @RequestLine("POST /api/user")
    User saveUser();
    @RequestLine("POST /api/user/bulk/{bulkSize}")
    List<User> saveUsers(@Param("bulkSize") int bulkSize);
}
