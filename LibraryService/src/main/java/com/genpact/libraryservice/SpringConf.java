package com.genpact.libraryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.genpact.libraryservice.controller", "com.genpact.libraryservice.service"})
public class SpringConf implements WebMvcConfigurer{

	@Value("${library.list.of.strings}")
	private List<String> libraryList;
	
	@Bean public ConversionService conversionService() {
	    return new DefaultConversionService();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
	
	@Bean
	@Qualifier("listLibrary")
	public List<String> getDefaultLibrary() {
		return libraryList;
	}
}
