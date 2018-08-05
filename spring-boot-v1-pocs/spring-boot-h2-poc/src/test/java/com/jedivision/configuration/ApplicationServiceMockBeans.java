package com.jedivision.configuration;

import com.jedivision.service.EquipmentService;
import com.jedivision.service.JediService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationServiceMockBeans {

	@Bean
	public JediService jediService() {
		return mock(JediService.class);
	}

	@Bean
	public EquipmentService equipmentService() {
		return mock(EquipmentService.class);
	}
}
