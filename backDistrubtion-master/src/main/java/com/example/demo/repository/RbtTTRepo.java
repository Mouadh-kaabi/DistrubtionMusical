package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.RbtTT;

public interface RbtTTRepo  extends JpaRepository<RbtTT, Integer>{

	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE rbttt u SET u.name = :name  , u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = (select * from (select max(id) from rbttt)xx)")
	public void savenewIn(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);
	
	
	
	
	

@Query(nativeQuery = true, value = "select * \n"
			+ "from rbttt  where   idartiste_chasnon  LIKE CONCAT((select id FROM artiste u where u.id=:id),'%')\n")
	List<Object[]> RbtTTByArtiste(@Param("id") Integer id);
	
	
	
	

@Query(nativeQuery = true, value = "select  * \n"
			+ "from rbttt  where   idartiste_chasnon  LIKE CONCAT((select id FROM artiste u where u.id=:id),'%')\n")
	List<Object[]> chansonByArtiste(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select  * \n"
			+ "from rbttt  where   content_provider_id  LIKE CONCAT((select id FROM content_provider u where u.id=:id),'%')\n")
	List<Object[]> chansonByCp(@Param("id") Integer id);
}
