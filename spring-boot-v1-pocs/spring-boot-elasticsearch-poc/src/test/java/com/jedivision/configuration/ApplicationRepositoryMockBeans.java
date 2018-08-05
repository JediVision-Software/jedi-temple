package com.jedivision.configuration;

import com.jedivision.repository.JediRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationRepositoryMockBeans {

    @Bean
    public JediRepository jediRepository() {
        return mock(JediRepository.class);
    }
}
