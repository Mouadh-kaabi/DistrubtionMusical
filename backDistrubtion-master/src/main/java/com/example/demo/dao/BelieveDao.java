package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BelieveRepo;

@Service
public class BelieveDao {

	@Autowired
	BelieveRepo believeRepo;

	public List<Object[]> getAllDateDeezer() {
		return believeRepo.statDate();
	}

	public List<Object[]> getAllArtistBelieve() {
		return believeRepo.statArtisteBeelive();
	}

	public List<Object[]> getAllChansonBelieve() {
		return believeRepo.statChansonBelieve();
	}

	public List<Object[]> getAllPlatformeBelieve() {
		return believeRepo.statPlateformeBelieve();
	}

}
