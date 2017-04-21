package com.jedivision.configuration;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationFallBackProviderBeans {
    @Bean
    public ZuulFallbackProvider taskFallbackProvider() {
        return ServiceFallbackProvider
                .builder()
                .route("task-service")
                .build();
    }

    @Bean
    public ZuulFallbackProvider userFallbackProvider() {
        return ServiceFallbackProvider
                .builder()
                .route("user-service")
                .build();
    }
}
