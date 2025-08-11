package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class EklecticDao {

	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllElectic() {
		return tunisieTelecomeRepo.statEKLECTIC();
	}

	public List<Object[]> getAllArtisteEklectic() {
		return tunisieTelecomeRepo.statArtisteEKLECTIC();
	}

	public List<Object[]> getAllChanosnEklectic() {
		return tunisieTelecomeRepo.statChansonEkLECTIC();
	}

	public List<Object[]> getAllCatSubCatEklectic() {
		return tunisieTelecomeRepo.statSouCategoryEklectic();
	}

}
