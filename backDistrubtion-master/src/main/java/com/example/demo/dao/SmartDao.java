package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class SmartDao {

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	
	public List<Object[]>getAllSmart()
	{
		return tunisieTelecomeRepo.statSmart();
	}
	
	
	
	
	public List<Object[]>getAllArtisteSmart()
	{
		return tunisieTelecomeRepo.statArtisteSmart();
	}
	
	//statArtisteSmartMusicBee
	
	public List<Object[]>getAllArtisteMusic()
	{
		return tunisieTelecomeRepo.statArtisteMusicBee();
	}
	public List<Object[]>getAllChanosnSmart()
	{
		return tunisieTelecomeRepo.statChansonSmart();
	}
	
	
	public List<Object[]>getAllCatSubCatSmart()
	{
		return tunisieTelecomeRepo.statSouCategorySmart();
	}
	
	
}
