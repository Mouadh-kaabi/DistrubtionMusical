package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ConfigTtDao;
import com.example.demo.entities.ConfigTT;

@RestController
@RequestMapping("/configTT")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class ConfigTTRest {

	
	@Autowired
	ConfigTtDao configTtDao ;

	@PostMapping("/add")
	public ConfigTT ajouterConf(@RequestBody ConfigTT configTT) {
		return configTtDao.ajouterConf(configTT);
	}
	
	
	
}
