package com.jedivision.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationElasticMockBeans {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return mock(ElasticsearchTemplate.class);
    }
}
