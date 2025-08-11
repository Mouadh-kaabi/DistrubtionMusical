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

import com.example.demo.dao.PlatformeDao;
import com.example.demo.entities.Platforme;
import com.example.demo.repository.PlatformeRepo;

@RestController
@RequestMapping("/platforme")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class PlatformeRest {

	
	@Autowired
	PlatformeDao platformeDao ;
	
	@Autowired
	PlatformeRepo platformeRepo ; 
	
	
	@PostMapping("/addpaltforme")
	public Platforme ajouterPlatforme(@RequestBody Platforme pt) {
		return platformeDao.ajouterPlatforme(pt);
	}

	@GetMapping("/all")
	public List<Platforme> getAllPlatforme() {
		return platformeDao.getAllPlatforme();
	}

	@GetMapping("/by-id/{id}")
	public Platforme getPlatforme(@PathVariable(value = "id") Integer id) {
		return platformeDao.getPlatforme(id);
	}
	@PutMapping("/update")
	public Platforme updatePlatforme(@RequestBody Platforme platforme) {
		return platformeDao.updatePlatforme(platforme);
	} 
	
	
	@DeleteMapping("/deletplatforme/{id}")
	public void deleteAdminId(@PathVariable("id") Integer id) {
		platformeRepo.deleteById(id);
	}
	
}
