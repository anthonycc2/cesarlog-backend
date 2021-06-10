package org.cesar.CesarLog.controller.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
	
	@Bean
	public EmailController emailController() {
		return new SmtpEmailController();
	}

}
