package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ConfigTT;
import com.example.demo.repository.ConfigTTRepo;

@Service
public class ConfigTtDao {

	
	@Autowired
	ConfigTTRepo configTTRepo;
	
	
	public ConfigTT ajouterConf(ConfigTT configTT)
	{
		return configTTRepo.save(configTT);
	}
}
