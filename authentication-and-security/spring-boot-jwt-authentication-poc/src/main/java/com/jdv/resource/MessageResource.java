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
				.text("Public API")
				.build();
	}

	@GetMapping("/private")
	public Message roleUser(Principal principal) {
		LOGGER.debug("Spring BasicAuth Security: Private API");
		return Message.builder()
				.text("Private API")
				.build();
	}
}
