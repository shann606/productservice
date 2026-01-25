package com.ecom.productservice.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class Appconfig {
	
	@Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of("System");
    }

	
}
