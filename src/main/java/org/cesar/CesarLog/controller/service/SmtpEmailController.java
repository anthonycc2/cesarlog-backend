package org.cesar.CesarLog.controller.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailController extends AbstractEmailController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailController.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Realizando envio de email...");
		mailSender.send(msg);
		LOG.info("Email enviado!");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Realizando envio de email...");
		javaMailSender.send(msg);
		LOG.info("Email enviado!");
	}

}
