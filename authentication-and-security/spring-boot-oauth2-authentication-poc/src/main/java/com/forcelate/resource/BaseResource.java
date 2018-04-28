package com.forcelate.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class BaseResource {

    @GetMapping("/message")
    @ResponseStatus(value = HttpStatus.OK)
    public String message() {
        LOGGER.info("API message POST method executed successfully");
        return "Success";
    }
}
