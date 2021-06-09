package org.cesar.CesarLog.controller;

import java.util.Optional;
import org.cesar.CesarLog.model.entity.Allocation;
import org.cesar.CesarLog.model.repository.AllocationRepository;
import org.cesar.CesarLog.model.repository.EmployeeRepository;
import org.cesar.CesarLog.model.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/allocation")
public class AllocationController {
	
	@Autowired
	private AllocationRepository repository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String insert(@RequestBody Allocation allocation) {
		boolean equipmentExists = equipmentRepository.existsById(allocation.getEquipment().getId());
		boolean employeeExists = employeeRepository.existsById(allocation.getEmployee().getId());
		
		if(equipmentExists && employeeExists) {
			repository.save(allocation);
			return "Saved";
		} else {
			return "Id doesn't exist";
		}
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Allocation> list() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/employee/{id}")
	public @ResponseBody Iterable<Allocation> listByEmployee(@PathVariable(required = true, name="id") int id) {
		return repository.findByEmployee(id);
	}
	
	@GetMapping(path = "/project/{id}")
	public @ResponseBody Iterable<Allocation> listByProject(@PathVariable(required = true, name="id") int id) {
		return repository.findByProject(id);
	}
	
	@GetMapping(path = "/get/{id}")
	public @ResponseBody Optional<Allocation> recover(@PathVariable(required = true, name="id") int id) {
			return repository.findById(id);
	}
	
	@PutMapping(path = "/update")
	public @ResponseBody String update(@RequestBody Allocation allocation) {
		boolean allocationExists = repository.existsById(allocation.getId());
		boolean equipmentExists = equipmentRepository.existsById(allocation.getEquipment().getId());
		boolean employeeExists = employeeRepository.existsById(allocation.getEmployee().getId());
				
		if(allocationExists && equipmentExists && employeeExists) {
			repository.save(allocation);
			return "Saved";			
		} else {
			return "Id doesn't exist";
		}
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody String delete(@PathVariable(required = true, name = "id") int id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Removed";
		} else {
			return "Id doesn't exist";
		}
	}

}
