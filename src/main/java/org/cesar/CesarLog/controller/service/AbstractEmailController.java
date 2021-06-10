package org.cesar.CesarLog.controller.service;

import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.cesar.CesarLog.model.entity.Employee;
import org.cesar.CesarLog.model.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailController implements EmailController {
	
	//default.sender=${MAIL_USERNAME}
	@Value("${default.sender}")
	private String sender;
		
	//@Autowired
	//private AccountService accountService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendAlertEmail(Employee obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromEmployee(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromEmployee(Employee employee) {

		//Integer accountId = employee.getAccountId();
		//Account account = accountService.findById(accountId);
		String email = employee.getEmail();
		//List<Equipment> equipments = employee.getEquipment();
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(email);
		sm.setFrom(sender);
		sm.setSubject("Alerta de alocação de equipamento");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		//sm.setText("Olá" + employee.getName() + "Essa é uma mensagem padrão de alerta de alocação de equipamento\n" + equipments);
		sm.setText("Olá" + employee.getName() + "Essa é uma mensagem padrão de alerta de alocação de equipamentos");
		
		return sm;
	}
	
	protected String htmlFromTemplateEmployee(Employee obj, ArrayList<Equipment> equipments) {
		Context context = new Context();
		context.setVariable("employee", obj);
		context.setVariable("equipments", equipments);
		return templateEngine.process("email/alertEmail", context);
	}
	
	@Override
	public void sendAlertHtmlEmail(Employee obj, ArrayList<Equipment> equipments) {
		try {
			MimeMessage mm = prepareMimeMessageFromEmployee(obj, equipments);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendAlertEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromEmployee(Employee employee, ArrayList<Equipment> equipments) throws MessagingException {
		
		/*Integer accountId = employee.getAccountId();
		Account account = accountService.findById(accountId);
		String email = account.getEmail();*/
		String email = employee.getEmail();
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

		mmh.setTo(email);
		mmh.setFrom(sender);
		mmh.setSubject("Alerta de alocação de equipamento");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateEmployee(employee, equipments), true);

		return mimeMessage;
	}

}
