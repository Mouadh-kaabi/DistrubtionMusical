package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.repository.RoleRepo;

@Service
public class RoleDao {

	
	@Autowired
	
	RoleRepo roleRepo ;
	
	
	public Role addRole(Role r) {
		roleRepo.save(r);
		return r;
	}
}
