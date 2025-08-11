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

import com.example.demo.dao.FournisseurDao;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Fournisseur;
import com.example.demo.repository.FournisseurRepo;

@RestController
@RequestMapping("/fournisseur")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class FourinsseurRest {

	
	@Autowired
	FournisseurDao fournisseurDao ; 
	
	@Autowired
	FournisseurRepo fournisseurRepo ;

	@PostMapping("/inscriptionFournissueur")
	public Fournisseur inscrpition(@RequestBody Fournisseur ad) {
	return fournisseurDao.ajouterFournisseur(ad);
	}
	
	
	
	
	@GetMapping("/all")
	public List<Fournisseur> getAllFournisseurs() {
		return fournisseurDao.getAllFournisseurs();
	}
	
	
	
	@GetMapping("/{id}")
	public Fournisseur getFournisseur(@PathVariable(value = "id") Integer id) {
		return fournisseurDao.getFournisseur(id);
	}
	
	
	
	@PutMapping("/update")
	public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurDao.updateFournisseur(fournisseur);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteFourId(@PathVariable("id") Integer id) {
		fournisseurRepo.deleteById(id);
		
	}
}
