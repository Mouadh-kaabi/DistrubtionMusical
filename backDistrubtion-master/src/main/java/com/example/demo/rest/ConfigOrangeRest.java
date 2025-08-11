package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ConfigOrangeDao;
import com.example.demo.entities.ConfigOrange;

@RestController
@RequestMapping("/configOrange")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class ConfigOrangeRest {
	
	@Autowired
	ConfigOrangeDao configOrangeDao ;

	@PostMapping("/add")
	public ConfigOrange ajouterConf(@RequestBody
			ConfigOrange configOrange) {
		return configOrangeDao.ajouterConf(configOrange);
	} 
	
	

}
