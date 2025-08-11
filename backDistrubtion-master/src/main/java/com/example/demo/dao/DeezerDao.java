package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.DeezerRepo;

@Service
public class DeezerDao {

	@Autowired
	DeezerRepo deezerRepo;

	public List<Object[]> getAllDateDeezer() {
		return deezerRepo.statDate();
	}

	public List<Object[]> getAllArtisteDeezer() {
		return deezerRepo.statArtisteDeezer();
	}

	// statFournisseurDeezer

	public List<Object[]> getAllFournisseurDeezer() {
		return deezerRepo.statFournisseurDeezer();
	}

	public List<Object[]> getAllChansonDeezer() {
		return deezerRepo.statChansonDeezer();
	}

}
