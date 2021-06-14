package org.cesar.CesarLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CesarLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CesarLogApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE");
				registry.addMapping("/**").allowedOrigins("https://cesarlog.netlify.app").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
