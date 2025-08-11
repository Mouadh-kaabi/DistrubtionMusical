package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class CP_TunisieDao {

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllCpTunisie() {
		return tunisieTelecomeRepo.statCpTunisi();
	}

	public List<Object[]> getAllArtisteSpTunisie() {
		return tunisieTelecomeRepo.statArtisteCpTunisie();
	}

	
	public List<Object[]> getAllChanosnArpuPlus() {
		return tunisieTelecomeRepo.statChansonCpTunisie();
	}

	public List<Object[]> getAllCatSubCatArpuPlus() {
		return tunisieTelecomeRepo.statSouCategoryCpTunisie();
	}
}
