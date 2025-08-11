package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service

public class ArpuPlusDao {

	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;

	public List<Object[]> getAllArpuPlus() {
		return tunisieTelecomeRepo.statARPUPlus();
	}

	
	//getMusicBee
	
	public List<Object[]> getMusicBee() {
		return tunisieTelecomeRepo.statMusicBee();
	}
	
	
	public List<Object[]> getChrome() {
		return tunisieTelecomeRepo.statChrome();
	}
	//Digitalsound
	
	public List<Object[]> getDigitalsound() {
		return tunisieTelecomeRepo.statDigitalsound();
	}
	public List<Object[]> getAllArtisteArpuPlus() {
		return tunisieTelecomeRepo.statArtisteARPU();
	}
	
	public List<Object[]> getAllArtisteMusicBee() {
		return tunisieTelecomeRepo.statArtisteMusicBee();
	}
	//Digitalsound
	public List<Object[]> getAllArtisteDigitalsound() {
		return tunisieTelecomeRepo.statArtisteDigitalsound();
	}
	
	public List<Object[]> getAllArtisteChrome() {
		return tunisieTelecomeRepo.statArtisteChrome();
	}
	public List<Object[]> getAllChanosnArpuPlus() {
		return tunisieTelecomeRepo.statChansonMusicBee();
	}
	
	public List<Object[]> getAllChanosnMusicBee() {
		return tunisieTelecomeRepo.statChansonMusicBee();
	}
	
	
	public List<Object[]> getAllChanosnChrome() {
		return tunisieTelecomeRepo.statChansonChrome();
	}
	
	public List<Object[]> getAllChanosDisitalSound() {
		return tunisieTelecomeRepo.statChansonDigitalSound();
	}
	//Digitalsound

	public List<Object[]> getAllDigitalsound() {
		return tunisieTelecomeRepo.statChansonMusicBee();
	}
	public List<Object[]> getAllCatSubCatArpuPlus() {
		return tunisieTelecomeRepo.statSouCategoryARPU();
	}
}