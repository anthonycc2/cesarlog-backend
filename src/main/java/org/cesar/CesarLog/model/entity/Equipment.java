package org.cesar.CesarLog.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
			
	private String observation;
	
	private boolean maintenance;

	public Equipment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public boolean isMaintenance() {
		return maintenance;
	}

	public void setMaintenance(boolean maintenance) {
		this.maintenance = maintenance;
	}

}
