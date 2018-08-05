package com.jedivision.resource;

import com.jedivision.entity.Version;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AnonymousResource {

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public Version getVersion() {
        return Version.builder()
                .uuid(UUID.randomUUID().toString())
                .date(new Date())
                .description("Spring Boot HTTP Basic 1.4.1.RELEASE")
                .build();
    }
}
