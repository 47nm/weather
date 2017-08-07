package com.lift.weather.server.initializers;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class WeatherServerInitializer implements ServletContainerInitializer {
	
	@Override
	public void onStartup(Set<Class<?>> clazzSet, ServletContext servletContext) throws ServletException {
		System.out.println("Weather");
	}
	
	
}
