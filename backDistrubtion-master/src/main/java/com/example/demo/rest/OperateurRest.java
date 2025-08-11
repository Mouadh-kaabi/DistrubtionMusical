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

import com.example.demo.dao.OperateurDao;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Operateur;
import com.example.demo.repository.OperateurRepo;

@RestController
@RequestMapping("/operateur")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class OperateurRest {

	
	@Autowired
	OperateurRepo operateurRepo ;
	
	@Autowired
	
	OperateurDao operateurDao ;
	
	
	
	
	
	@PostMapping("/inscription")
	public Operateur inscrpition(@RequestBody Operateur ad) {
	return operateurDao.ajouterOperateur(ad);
	}
	
	@GetMapping("/all")
	public List<Operateur> getAllAdmins() {
		return operateurDao.getAllOperateurs();
	}
	
	
	
	@GetMapping("/by-id/{id}")
	public Operateur getArtiste(@PathVariable(value = "id") Integer id) {
		return operateurDao.getOperateur(id);
	}
	
	
	
	@PutMapping("/update")
	public Operateur updateOperateur(@RequestBody Operateur operateur) {
		return operateurDao.updateOperateur(operateur);
	}
	
	
	@DeleteMapping("/deleteoperateur/{id}")
	public void deleteoperateurId(@PathVariable("id") Integer id) {
		operateurRepo.deleteById(id);
	}
	
	
	
}
