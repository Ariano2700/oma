package com.oma2.oma20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppOMA implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AppOMA.class, args);
	}
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowedHeaders("*");
	}


}
