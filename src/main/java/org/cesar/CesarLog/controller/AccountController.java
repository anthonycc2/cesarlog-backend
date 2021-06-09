package org.cesar.CesarLog.controller;

import java.util.Optional;
import org.cesar.CesarLog.model.entity.Account;
import org.cesar.CesarLog.model.repository.AccountRepository;
import org.cesar.CesarLog.model.repository.EmployeeRepository;
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
@RequestMapping(path = "/account")
public class AccountController {
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String insert(@RequestBody Account account) {
		boolean employeeExists = employeeRepository.existsById(account.getEmployee().getId());
		
		if (employeeExists) {
			repository.save(account);
			return "Saved";
		} else {
			return "Id doesn't exist";
		}
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Account> list() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/get/{id}")
	public @ResponseBody Optional<Account> recover(@PathVariable(required = true, name="id") int id) {
			return repository.findById(id);
	}
	
	@GetMapping(path = "/login/{login}")
	public @ResponseBody Optional<Account> recoverByLogin(@PathVariable(required = true, name="login") String login) {
		return repository.findBylogin(login);
	}
	
	@PutMapping(path = "/update")
	public @ResponseBody String update(@RequestBody Account account) {
		boolean accountExists = repository.existsById(account.getId());
		boolean employeeExists = employeeRepository.existsById(account.getEmployee().getId());
		
		if (accountExists && employeeExists) {
			repository.save(account);
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

