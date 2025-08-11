package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class TTDao {

	
	
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	
	
	//statDateTT
	
	public List<Object[]> statDateTT ()
	{
		return tunisieTelecomeRepo.statDateTT();
	}
	
	
	//statArtisteTT
	public List<Object[]> statArtsiteTT ()
	{
		return tunisieTelecomeRepo.statArtisteTT();
	}
	
	//statChansonTT
	
	public List<Object[]> statChansonTT ()
	{
		return tunisieTelecomeRepo.statChansonTT();
	}
	
	//statcategoryTT
	
	public List<Object[]> statcategoryTT ()
	{
		return tunisieTelecomeRepo.statcategoryTT();
	}
	
	
	
}
