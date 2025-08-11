package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Platforme;
import com.example.demo.entities.Role;
import com.example.demo.repository.PlatformeRepo;

@Service
public class PlatformeDao {

	
	
	@Autowired
	PlatformeRepo platformeRepo ;
	
	
public Platforme ajouterPlatforme(Platforme pt)  {
		
	
	platformeRepo.save(pt);
		
		return pt;
	}
	
	
	 public List<Platforme> getAllPlatforme() {
			return platformeRepo.findAll();
		}
	    
	    
	    
	    public Platforme getPlatforme(Integer id) {

			return platformeRepo.findById(id).get();

		}
	
	    
	    public Platforme updatePlatforme(Platforme platforme) {
			if (platforme.getId() != 0) {
				Platforme pt = platformeRepo.findById(platforme.getId()).get();
				if (pt != null) {
					platformeRepo.save(platforme);
				}
			}
			return platforme;
		}
	
}
