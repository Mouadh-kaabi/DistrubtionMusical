package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class SpTunisiDao {

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllSpTunisi() {
		return tunisieTelecomeRepo.statSpTunisi();
	}

	public List<Object[]> getAllArtisteSpTunisie() {
		return tunisieTelecomeRepo.statArtisteSP_Tunisie();
	}

	public List<Object[]> getAllChanosnArpuPlus() {
		return tunisieTelecomeRepo.statChansonSP_Tunisie();
	}

	public List<Object[]> getAllCatSubCatArpuPlus() {
		return tunisieTelecomeRepo.statSouCategorySP_Tunisie();
	}
}
