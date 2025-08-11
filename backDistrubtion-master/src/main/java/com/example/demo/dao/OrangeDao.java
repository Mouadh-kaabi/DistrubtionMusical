package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.OrangeRepo;

@Service
public class OrangeDao {

	@Autowired
	OrangeRepo orangeRepo;

	public List<Object[]> statDate() {
		return orangeRepo.statDate();
	}

	public List<Object[]> statArtisteOrange() {
		return orangeRepo.statArtisteOrange();
	}

	
	//statArtistOrangeUnti 
	
	/*public List<Object[]> statArtisteOrangeUnit() {
		return orangeRepo.statArtistOrangeUnti();
	}*/ 
	public List<Object[]> statArtisteOrangeUnit(String namea) {
	    return orangeRepo.statArtistOrangeUnti(namea);
	}
	
	public List<Object[]> statArtisteTTUnit() {
		return orangeRepo.statArtistTTUnti();
	} 
	public List<Object[]> statChansonOrange() {
		return orangeRepo.statChansonOrange();
	}

	// statPlateforme
	public List<Object[]> statPlatformeOrange() {
		return orangeRepo.statPlateformeOrange();
	}

	// statcategoryOrange

	public List<Object[]> statCategoryOrange() {
		return orangeRepo.statcategoryOrange();
	}

	//
}
