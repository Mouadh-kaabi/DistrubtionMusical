package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Operateur;
import com.example.demo.entities.Role;
import com.example.demo.repository.OperateurRepo;

@Service

public class OperateurDao {

	@Autowired
	OperateurRepo operateurRepo;

	public Operateur ajouterOperateur(Operateur pt) {

		return operateurRepo.save(pt);
	}

	public List<Operateur> getAllOperateurs() {
		return operateurRepo.findAll();
	}

	public Operateur getOperateur(Integer id) {

		return operateurRepo.findById(id).get();

	}

	public Operateur updateOperateur(Operateur operateur) {
		if (operateur.getId() != 0) {
			Operateur pt = operateurRepo.findById(operateur.getId()).get();
			if (pt != null) {
				operateurRepo.save(operateur);
			}
		}
		return operateur;
	}

}
