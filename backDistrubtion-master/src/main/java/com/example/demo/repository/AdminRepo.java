package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Users;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	
	Optional<Admin> findByEmail(String email);
	public boolean existsByEmail(String email);
	
	List<Admin> findByemail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE admin art set art.password = ?1 WHERE art.id = ?2")
	void changePWD(String password, Integer id);
	
	List<Admin> findByToken(String token);
	
	
	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE admin u SET u.name = :name ,  u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = :id")
	public void savenew(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type, @Param("id") Integer id);
}
