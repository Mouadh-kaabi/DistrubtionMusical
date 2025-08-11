package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RoleDao;
import com.example.demo.entities.Role;

@RestController
@RequestMapping("/role")
@CrossOrigin("http://192.168.1.111:4200")
public class RoleRest {

	@Autowired
	RoleDao rd;
	
	@PostMapping("/add")
	public Role addRole(@RequestBody Role r) {
		return rd.addRole(r);
	}
	
}
