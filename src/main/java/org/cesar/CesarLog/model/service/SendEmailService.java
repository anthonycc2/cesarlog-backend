package org.cesar.CesarLog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail() {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("equipecovida@gmail.com");
		sm.setTo("anthoncc2@gmail.com");
		sm.setSubject("CesarLog");
		sm.setText("TESTE CesarLog > Se vc está lendo isso, o teste deu certo!");
		javaMailSender.send(sm);

		System.out.println("Enviando email...");
	}

}
