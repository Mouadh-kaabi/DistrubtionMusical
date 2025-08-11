package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Album;

public interface AlbumRepo  extends JpaRepository<Album, Integer>{

	
	
	@Query(nativeQuery = true, value ="select *  from album    \r\n"  )//
	List<Album> listAlbum();
	
	
	
	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE album u SET u.name = :name ,  u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = :id")
	public void savenew(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type, @Param("id") Integer id);
	
	
	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE album u SET u.name = :name  , u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = (select * from (select max(id) from album)xx)")
	public void savenewIn(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);
}
