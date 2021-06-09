package org.cesar.CesarLog.controller;

import org.cesar.CesarLog.model.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/email")
public class SendEmailController {
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@PostMapping
	public @ResponseBody String send() {
		try {
			sendEmailService.sendEmail();
			return "Email sent";
		} catch (MailException e) {
			e.printStackTrace();
			return "Email NOT sent";
		}
		
	}
}

