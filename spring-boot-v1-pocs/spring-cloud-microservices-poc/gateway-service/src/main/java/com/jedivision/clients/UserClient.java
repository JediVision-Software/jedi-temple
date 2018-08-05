package com.jedivision.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/index", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void init();
}
