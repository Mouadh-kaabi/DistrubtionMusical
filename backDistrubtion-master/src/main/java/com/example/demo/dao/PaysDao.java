package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Pays;
import com.example.demo.entities.Role;
import com.example.demo.repository.PaysRepo;

@Service
public class PaysDao {

	@Autowired
	PaysRepo paysRepo;

	public Pays ajouterPays(Pays pays) {

		paysRepo.save(pays);

		return pays;
	}

	public List<Pays> getAllpays() {
		return paysRepo.findAll();
	}

	public Pays getPays(Integer id) {

		return paysRepo.findById(id).get();

	}

	public Pays updatePays(Pays pays) {
		if (pays.getId() != 0) {
			Pays pt = paysRepo.findById(pays.getId()).get();
			if (pt != null) {
				paysRepo.save(pays);
			}
		}
		return pays;
	}
}
