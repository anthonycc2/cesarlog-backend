package org.cesar.CesarLog.controller.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.cesar.CesarLog.model.entity.Employee;
import org.cesar.CesarLog.model.entity.Allocation;
import org.cesar.CesarLog.model.entity.Equipment;
import org.cesar.CesarLog.model.repository.AllocationRepository;
import org.cesar.CesarLog.model.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Calendar cal = Calendar.getInstance();

	private static final Integer TOLERANCE = 1;

	@Autowired
	private AllocationRepository allocationRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmailController emailController;

	@Scheduled(fixedRate = 86400000) // miliseconds
	public void reportCurrentTime() throws ParseException {
		
		Iterable<Employee> employees = employeeRepository.findAll();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");

		for (Employee employee : employees) {

			Date today = new Date();
			Integer employeeId = employee.getId();

			Iterable<Allocation> allocations = allocationRepository.findByEmployee(employeeId);
			ArrayList<Equipment> equipmentsFiltered = new ArrayList<>();

			for (Allocation allocation : allocations) {

				Date dateStatus = formatter.parse(allocation.getLocationDate());
				//Date dateStatus = Date.parse(allocation.getLocationDate());

				cal.setTime(dateStatus);
				cal.add(Calendar.DATE, TOLERANCE);
				Date newDateStatus = cal.getTime();

				if (today.after(newDateStatus)) {
					equipmentsFiltered.add(allocation.getEquipment());
				}
			}

			emailController.sendAlertHtmlEmail(employee, equipmentsFiltered);
			//emailService.sendAlertEmail(employee);

		}

	}

}
