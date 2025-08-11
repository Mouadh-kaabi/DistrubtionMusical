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
import com.example.demo.intefrace.ArtisteGe;

public interface ArtisteRepo extends JpaRepository<Artiste, Integer> {

	
	//Optional<Admin> findByEmail(String email);
		public boolean existsByEmail(String email);
		
		
		
		@Query(nativeQuery = true, value ="\r\n" + 
				"insert into artiste(id,namea)"+
				" select   id, namea  from tunisie_telecom "
				 )
		List<ArtisteGe> listNomArtiste();
		
		/*@Modifying 
		@Transactional
		@Query(nativeQuery = true, value = "INSERT INTO artiste (n_artistique)"
				)
		public void saveArtiste(@Param("nartistique") String nartistique);*/
		
		@Modifying 
		@Transactional
		@Query(nativeQuery = true, value = "UPDATE artiste u SET u.name = :name ,  u.pic_byte = :picByte ,u.type = :type "
				+ "where u.id = :id")
		public void savenew(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type, @Param("id") Integer id);
		
		
		@Modifying 
		@Transactional
		@Query(nativeQuery = true, value = "UPDATE artiste u SET u.name = :name  , u.pic_byte = :picByte ,u.type = :type "
				+ "where u.id = (select * from (select max(id) from album)xx)")
		public void savenewIn(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);
		
		
		@Modifying
		@Transactional
		@Query("UPDATE artiste art set art.password = ?1 WHERE art.id = ?2")
		void changePWD(String password, Integer id);
		
		
		List<Artiste> findByEmail(String email);
		
		List<Artiste> findByToken(String token);

		

		List<Artiste> findByemail(String email);


		List<Artiste> findBynArtistique(String nArtistique);
	
		
		@Query("SELECT u FROM artiste u  join u.roles r where r.name ='ARTISTE' and u.nArtistique =:nom")
		List<Artiste> findByNom(@Param("nom") String nom);
	
		
		
}
