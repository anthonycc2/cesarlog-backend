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
import org.cesar.CesarLog.model.entity.Project;
import org.cesar.CesarLog.model.repository.ProjectRepository;

@Controller
@RequestMapping(path = "/project")
public class ProjectController {
	
	@Autowired
	private ProjectRepository repository;

	@PostMapping(path = "/add")
	public @ResponseBody String insert(@RequestBody Project project) {
		repository.save(project);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Project> list() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/get/{id}")
	public @ResponseBody Optional<Project> recover(@PathVariable(required = true, name="id") int id) {
			return repository.findById(id);
	}
	
	@PutMapping(path = "/update")
	public @ResponseBody String update(@RequestBody Project project) {
		if (repository.existsById(project.getId())) {
			repository.save(project);
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
