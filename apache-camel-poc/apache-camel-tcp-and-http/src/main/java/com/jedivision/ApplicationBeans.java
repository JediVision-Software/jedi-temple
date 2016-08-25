package com.jedivision;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {

    @Bean
    public ObiWanBean exitBean() {
        return new ObiWanBean();
    }

    @Bean
    public CamelContext camelContext() {
        return new DefaultCamelContext();
    }

}
