package com.jedivision.test.runner;

import com.jedivision.configuration.ApplicationElasticMockBeans;
import com.jedivision.configuration.ApplicationRepositoryMockBeans;
import com.jedivision.repository.JediRepository;
import com.jedivision.service.JediService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class ApplicationServiceRunner {

    @Configuration
    @Import(value = {
            ApplicationRepositoryMockBeans.class,
            ApplicationElasticMockBeans.class
    })
    static class ContextConfiguration {

        @Autowired
        private JediRepository jediRepository;

        @Autowired
        private ElasticsearchTemplate elasticsearchTemplate;

        @Bean
        public JediService jediService() {
            return new JediService(jediRepository, elasticsearchTemplate);
        }
    }
}
