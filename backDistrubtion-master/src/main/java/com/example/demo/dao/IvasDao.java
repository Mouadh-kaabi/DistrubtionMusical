package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class IvasDao {

	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllIvas() {
		return tunisieTelecomeRepo.statIVAS();
	}

	public List<Object[]> getAllArtisteIvas() {
		return tunisieTelecomeRepo.statArtisteIvasTT();
	}

	public List<Object[]> getAllChanosnIvas() {
		return tunisieTelecomeRepo.statChansonIVAS();
	}

	public List<Object[]> getAllCatSubCatIvas() {
		return tunisieTelecomeRepo.statSouCategoryIvas();
	}

}
