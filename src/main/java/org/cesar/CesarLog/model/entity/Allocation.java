package org.cesar.CesarLog.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "alocation_date")
	private String allocationDate;
	
	@Column(name = "acceptation_date")
	private String acceptationDate;
	
	@Column(name = "return_date")
	private String returnDate;
	
	private String status;
		
	private String location;
	
	@Column(name = "location_date")
	private String locationDate;
	
	public Allocation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(String allocationDate) {
		this.allocationDate = allocationDate;
	}
	
	public String getAcceptationDate() {
		return acceptationDate;
	}

	public void setAcceptationDate(String acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationDate() {
		return locationDate;
	}

	public void setLocationDate(String locationDate) {
		this.locationDate = locationDate;
	}
	
}
