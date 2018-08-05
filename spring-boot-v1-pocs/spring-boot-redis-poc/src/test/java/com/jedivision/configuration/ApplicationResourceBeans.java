package com.jedivision.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.jedivision.resource")
@EnableWebMvc
public class ApplicationResourceBeans {
}
