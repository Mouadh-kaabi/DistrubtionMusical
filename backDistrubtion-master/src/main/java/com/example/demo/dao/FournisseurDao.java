package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Role;
import com.example.demo.repository.FournisseurRepo;
import com.example.demo.repository.RoleRepo;

@Service
public class FournisseurDao {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	FournisseurRepo fournisseurRepo;

	public Fournisseur ajouterFournisseur(Fournisseur pt) {

		// Optional<Role> r = roleRepo.findByRoleName("fournisseur");
		// pt.setRoles((Set<Role>) r.get());
		// pt.setPassword(cryptDAO.encryption(pt.getPassword()));
		// pt.setStatus(false);
		fournisseurRepo.save(pt);

		return pt;
	}

	public List<Fournisseur> getAllFournisseurs() {
		return fournisseurRepo.findAll();
	}

	public Fournisseur getFournisseur(Integer id) {

		return fournisseurRepo.findById(id).get();

	}

	public Fournisseur updateFournisseur(Fournisseur fournisseur) {
		if (fournisseur.getId() != 0) {
			Fournisseur pt = fournisseurRepo.findById(fournisseur.getId()).get();
			if (pt != null) {
				fournisseurRepo.save(fournisseur);
			}
		}
		return fournisseur;
	}

}
