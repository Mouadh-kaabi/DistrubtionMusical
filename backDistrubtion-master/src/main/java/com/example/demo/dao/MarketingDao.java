package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Marketing;
import com.example.demo.entities.Role;
import com.example.demo.repository.MarketingRepo;

@Service
public class MarketingDao {

	@Autowired
	MarketingRepo marketingRepo;

	public Marketing ajouterMarketing(Marketing pt) {

		return marketingRepo.save(pt);
	}

	public List<Marketing> getAllMarketings() {
		return marketingRepo.findAll();
	}

	public Marketing getMarketing(Integer id) {

		return marketingRepo.findById(id).get();

	}

	public Marketing updateMarketing(Marketing marketing) {
		if (marketing.getId() != 0) {
			Marketing pt = marketingRepo.findById(marketing.getId()).get();
			if (pt != null) {
				marketingRepo.save(marketing);
			}
		}
		return marketing;
	}
}
