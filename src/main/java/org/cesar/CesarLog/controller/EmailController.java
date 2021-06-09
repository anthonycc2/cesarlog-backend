package org.cesar.CesarLog.controller;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.cesar.CesarLog.model.entity.Employee;

public interface EmailController {
	
	void sendAlertEmail(Employee obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendAlertHtmlEmail(Employee obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
