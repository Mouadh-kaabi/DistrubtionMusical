package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Artiste;
import com.example.demo.entities.Label;

public interface LabelRepo extends JpaRepository<Label, Integer> {

	
	
	public boolean existsByEmail(String email);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE artiste art set art.password = ?1 WHERE art.id = ?2")
	void changePWD(String password, Integer id);
	
	
	List<Label> findByEmail(String email);
	List<Label> findByToken(String token);
}
