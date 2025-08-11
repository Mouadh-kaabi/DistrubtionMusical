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

import com.example.demo.dao.MarketingDao;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Marketing;
import com.example.demo.repository.MarketingRepo;

@RestController
@RequestMapping("/marketing")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class MarketingRest {

	@Autowired
	MarketingDao marketingDao ;
	
	@Autowired
	MarketingRepo marketingRepo;
	
	@PostMapping("/inscription")
	public Marketing inscrpition(@RequestBody Marketing ad) {
	return marketingDao.ajouterMarketing(ad);
	}
	
	
	
	
	@GetMapping("/all")
	public List<Marketing> getAllFournisseurs() {
		return marketingDao.getAllMarketings();
	}
	
	
	
	@GetMapping("/{id}")
	public Marketing getMarketing(@PathVariable(value = "id") Integer id) {
		return marketingDao.getMarketing(id);
	}
	
	
	
	@PutMapping("/update")
	public Marketing updateMarketing(@RequestBody Marketing marketing) {
		return marketingDao.updateMarketing(marketing);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteFourId(@PathVariable("id") Integer id) {
		marketingRepo.deleteById(id);
		
	}
}
