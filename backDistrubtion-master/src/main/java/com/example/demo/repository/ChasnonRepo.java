package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Chanson;

public interface ChasnonRepo extends JpaRepository<Chanson, Integer> {

	boolean existsChansonByFileNameEquals(String fileName);
	
	boolean existsChansonByNomEquals(String nom);
	
	
	
	/*@Query(nativeQuery = true, value = "UPDATE chanson u SET u.name = :name  , u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = (select * from (select max(id) from chanson)xx)")
	public void savenewIn(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);*/
	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE chanson u SET u.name = :name ,  u.pic_byte = :picByte ,u.type = :type ")
	public void savenew(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);
	
	

	
	
	
	@Query(nativeQuery = true, value = "select  * \n"
			+ "from chanson  where   idartiste_chasnon  LIKE CONCAT((select id FROM artiste u where u.id=:id),'%')\n")
	List<Object[]> chansonByArtiste(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select  * \n"
			+ "from chanson  where   content_provider_id  LIKE CONCAT((select id FROM content_provider u where u.id=:id),'%')\n")
	List<Object[]> chansonByArtisteCp(@Param("id") Integer id);
	
	//List<Chanson> findByIdArtisteChasnon(@Param("id") Integer id);
}
