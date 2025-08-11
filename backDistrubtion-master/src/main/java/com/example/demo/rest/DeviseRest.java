package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DeviseDao;
import com.example.demo.entities.Devise;
import com.example.demo.repository.DeviseRepo;

@RestController
@RequestMapping("/devise")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class DeviseRest {

	
	@Autowired
	DeviseDao deviseDao ;
	
	@Autowired
	DeviseRepo deviseRepo ;
	
	@PostMapping("/ajouterDevis")
	public Devise ajouterDevise(@RequestBody Devise pt) {
		return deviseRepo.save(pt);
	}

	@GetMapping("/all")
	public List<Devise> getAllDevises() {
		return deviseDao.getAllDevises();
	}

	@GetMapping("/{id}")
	public Devise getDevise(@PathVariable(value = "id") Integer id) {
		return deviseDao.getDevise(id);
	}
	
	@PutMapping("/upadte")
	public Devise updateDevise(@RequestBody Devise devise) {
		return deviseDao.updateDevise(devise);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteDevisId(@PathVariable("id") Integer id) {
		deviseRepo.deleteById(id);
	}
	
	
	
}
