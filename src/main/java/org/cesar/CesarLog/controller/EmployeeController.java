package org.cesar.CesarLog.controller;

import java.util.Optional;
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
import org.cesar.CesarLog.model.entity.Employee;
import org.cesar.CesarLog.model.repository.EmployeeRepository;
import org.cesar.CesarLog.model.repository.ProjectRepository;

@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ProjectRepository projectRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String insert(@RequestBody Employee employee) {
		boolean projectExists = projectRepository.existsById(employee.getProject().getId());
		
		if(projectExists) {
			repository.save(employee);
			return "Saved";
		} else {
			return "Id doesn't exist";
		}
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Employee> list() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/project/{id}")
	public @ResponseBody Iterable<Employee> listByProject(@PathVariable(required = true, name="id") int id) {
		return repository.findByProject(id);
	}
	
	@GetMapping(path = "/get/{id}")
	public @ResponseBody Optional<Employee> recover(@PathVariable(required = true, name="id") int id) {
			return repository.findById(id);
	}
	
	@PutMapping(path = "/update")
	public @ResponseBody String update(@RequestBody Employee employee) {
		boolean employeeExists = repository.existsById(employee.getId());
		boolean projectExists = projectRepository.existsById(employee.getProject().getId());

		if (employeeExists && projectExists) {
			repository.save(employee);
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
