package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.ERole;
import com.example.demo.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
	public Optional<Role> findByRoleName(String roleName);
	
	Optional<Role> findByName(ERole name);
}
