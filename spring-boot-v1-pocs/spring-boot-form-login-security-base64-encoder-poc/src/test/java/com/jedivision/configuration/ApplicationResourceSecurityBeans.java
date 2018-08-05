package com.jedivision.configuration;

import com.jedivision.resource.UserResource;
import com.jedivision.security.H2UserDetailsService;
import com.jedivision.service.UserService;
import com.jedivision.utils.Base64PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationResourceSecurityBeans {
    @Bean
    public H2UserDetailsService h2UserDetailsService() {
        return mock(H2UserDetailsService.class);
    }

    @Bean
    public Base64PasswordEncoder base64PasswordEncoder() {
        return mock(Base64PasswordEncoder.class);
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public UserResource userResource() {
        return new UserResource(userService());
    }
}