package com.dispute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/api/users/loggedUser").allowedOrigins("http://localhost:4200");
            	registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
            	registry.addMapping("/api/logIn").allowedOrigins("http://localhost:4200");
                registry.addMapping("/api/tournaments/").allowedOrigins("http://localhost:4200");
                registry.addMapping("/api/users/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
