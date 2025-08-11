package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ConfigOrange;
import com.example.demo.repository.ConfigOrangeRepo;

@Service
public class ConfigOrangeDao {

	
	@Autowired
	ConfigOrangeRepo configOrangeRepo ;  
	
	public ConfigOrange ajouterConf(ConfigOrange configOrange)
	{
		return configOrangeRepo.save(configOrange);
	}
}
