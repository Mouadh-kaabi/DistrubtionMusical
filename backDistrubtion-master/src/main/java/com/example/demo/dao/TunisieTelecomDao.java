package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;


@Service
public class TunisieTelecomDao {

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllTunisieTelecom() {
		return tunisieTelecomeRepo.statTunisiTelecom();
	}

	public List<Object[]> getAllArtisteTunisieTelecom() {
		return tunisieTelecomeRepo.statArtisteTunisieTelecom();
	}

	
	public List<Object[]> getAllChanosnTunisieTelecom() {
		return tunisieTelecomeRepo.statChansonTunisieTelecom();
	}

	public List<Object[]> getAllCatSubCatTunisieTelecom() {
		return tunisieTelecomeRepo.statSouCategoryTuniseTelecom();
	}
}
