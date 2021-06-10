package org.cesar.CesarLog.controller.service;

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.cesar.CesarLog.model.entity.Employee;
import org.cesar.CesarLog.model.entity.Equipment;

public interface EmailController {
	
	void sendAlertEmail(Employee obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendAlertHtmlEmail(Employee obj, ArrayList<Equipment> equipments);
	
	void sendHtmlEmail(MimeMessage msg);

}
