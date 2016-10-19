package com.jedivision.configuration;

import com.jedivision.controller.Pages;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationMVC extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName(Pages.LOGIN);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
