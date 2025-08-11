package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entities.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);
	public boolean existsByEmail(String email);
	
	List<Users> findByNom(String email);
	//List<Users> findByEmailAuth(String email);
	//nArtistique
	//List<Users> findByEmaList(String email);
	//List<Users> findByNartistique(String nArtistique);
	
	//findByEmail
	
	List<Users> findByToken(String token);
}
