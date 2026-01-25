package com.ecom.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecom.productservice.util.LogAllRequest;

@Configuration
public class InterceptConfig implements WebMvcConfigurer {

	private LogAllRequest req;

	public InterceptConfig(LogAllRequest req) {
		this.req = req;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(req);

	}

}
