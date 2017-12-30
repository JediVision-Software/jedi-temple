package com.jdv.resource;

import com.jdv.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api")
public class MessageResource {
    @GetMapping("/public")
    public Message permitAll() {
        LOGGER.debug("Spring BasicAuth Security: Public API");
        return Message.builder()
                .text("Public API: No Principal")
                .build();
    }

    @GetMapping("/private")
    public Message roleUser(Principal principal) {
        LOGGER.debug("Spring BasicAuth Security: Private API");
        return Message.builder()
                .principal(principal.getName())
                .text("Private API: User + Admin")
                .build();
    }

    @GetMapping("/onlyAdmin")
    public Message roleAdmin(Principal principal) {
        LOGGER.debug("Spring BasicAuth Security: Only Admin");
        return Message.builder()
                .principal(principal.getName())
                .text("Private API: Only Admin")
                .build();
    }
}
