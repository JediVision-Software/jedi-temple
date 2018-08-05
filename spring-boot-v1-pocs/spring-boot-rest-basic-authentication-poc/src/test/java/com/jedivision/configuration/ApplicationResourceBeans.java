package com.jedivision.configuration;

import com.jedivision.service.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan("com.jedivision.resource")
public class ApplicationResourceBeans {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "secret";
    public static final String ADMIN_ROLE = "ADMIN";

    public static final String USER_USERNAME = "user";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "USER";

    @Bean
    public EventService eventService() {
        return mock(EventService.class);
    }
}
