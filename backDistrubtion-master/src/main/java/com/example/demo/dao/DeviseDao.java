package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Devise;
import com.example.demo.entities.Marketing;
import com.example.demo.repository.DeviseRepo;

@Service
public class DeviseDao {

	@Autowired
	DeviseRepo deviseRepo;

	public Devise ajouterDevise(Devise pt) {

		return deviseRepo.save(pt);
	}

	public List<Devise> getAllDevises() {
		return deviseRepo.findAll();
	}

	public Devise getDevise(Integer id) {

		return deviseRepo.findById(id).get();

	}

	public Devise updateDevise(Devise devise) {
		if (devise.getId() != 0) {
			Devise pt = deviseRepo.findById(devise.getId()).get();
			if (pt != null) {
				deviseRepo.save(devise);
			}
		}
		return devise;
	}

}
