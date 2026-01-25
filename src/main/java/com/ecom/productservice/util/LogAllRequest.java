package com.ecom.productservice.util;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LogAllRequest implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 log.info("Request reahced at our system in UTC time:::::"+ Instant.now());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 log.info("Request processed at our system :::::"+ Instant.now());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 log.info("Response sent from our system  ::::"+ Instant.now());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	

}
