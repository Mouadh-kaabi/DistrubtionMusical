package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TunisieTelecomeRepo;

@Service
public class QanawatDao {

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	
	
	public List<Object[]>getAllQanwat()
	{
		return tunisieTelecomeRepo.statquanwatTotal();
	}
	
	
	//statArtisteQuanwat
	
	public List<Object[]>getAllArtisteQuanwat()
	{
		return tunisieTelecomeRepo.statArtisteQuanwat();
	}
	
	//statChansonQuanwat
	
	public List<Object[]>getAllChansonQuanwat()
	{
		return tunisieTelecomeRepo.statChansonQuanwat();
	}
	
	//statSouCategoryQuanwat
	public List<Object[]>getAllSouCategoryQuanwat()
	{
		return tunisieTelecomeRepo.statSouCategoryQuanwat();
	}
	
	
	
	
	
	
}
