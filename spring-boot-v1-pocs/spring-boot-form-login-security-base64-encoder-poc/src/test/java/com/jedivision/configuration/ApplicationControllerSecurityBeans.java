package com.jedivision.configuration;

import com.jedivision.controller.IndexController;
import com.jedivision.security.H2UserDetailsService;
import com.jedivision.service.UserService;
import com.jedivision.utils.Base64PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationControllerSecurityBeans {

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
    public IndexController indexController() {
	    return new IndexController(userService());
    }
}
