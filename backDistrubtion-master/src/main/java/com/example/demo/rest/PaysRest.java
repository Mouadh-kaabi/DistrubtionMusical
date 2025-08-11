package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PaysDao;
import com.example.demo.entities.Pays;
import com.example.demo.repository.PaysRepo;

@RestController
@RequestMapping("/pays")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class PaysRest {
	
	
	@Autowired
	PaysDao paysDao;
	
	@Autowired
	PaysRepo paysRepo ;
	
	
	@PostMapping("/add")
	public Pays ajouterPays(@RequestBody Pays pays) {
		return paysDao.ajouterPays(pays);
	}
	
	@GetMapping("/all")
	public List<Pays> getAllpays() {
		return paysDao.getAllpays();
	}
	
	@GetMapping("/{id}")
	public Pays getPays(@PathVariable(value = "id") Integer id) {
		return paysDao.getPays(id);
	}
	@PutMapping("/update")
	public Pays updatePays(@RequestBody Pays pays) {
		return paysDao.updatePays(pays);
	}
	
	
	@DeleteMapping("/deletePays/{id}")
	public void deletePaysId(@PathVariable("id") Integer id) {
		paysRepo.deleteById(id);
	}

}
