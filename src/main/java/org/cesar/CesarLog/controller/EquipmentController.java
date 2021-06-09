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
import org.cesar.CesarLog.model.entity.Equipment;
import org.cesar.CesarLog.model.repository.CategoryRepository;
import org.cesar.CesarLog.model.repository.EquipmentRepository;
import org.cesar.CesarLog.model.repository.ModelRepository;

@Controller
@RequestMapping(path = "/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelRepository modelRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String insert(@RequestBody Equipment equipment) {
		boolean categoryExists = categoryRepository.existsById(equipment.getCategory().getId());;
		boolean modelExists = modelRepository.existsById(equipment.getModel().getId());
		
		if(categoryExists && modelExists) {
			repository.save(equipment);
			return "Saved";
		} else {
			return "Id doesn't exist";
		}
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Equipment> list() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/get/{id}")
	public @ResponseBody Optional<Equipment> recover(@PathVariable(required = true, name="id") int id) {
			return repository.findById(id);
	}
	
	@PutMapping(path = "/update")
	public @ResponseBody String update(@RequestBody Equipment equipment) {
		boolean equipmentExists = repository.existsById(equipment.getId());
		boolean categoryExists = categoryRepository.existsById(equipment.getCategory().getId());;
		boolean modelExists = modelRepository.existsById(equipment.getModel().getId());
		
		if(equipmentExists && categoryExists && modelExists) {
			repository.save(equipment);
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
