package com.jedivision.configuration;

import com.jedivision.dao.JediDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationDaoMockBeans {

	@Bean
	public JediDao jediDao() {
		return mock(JediDao.class);
	}
}
