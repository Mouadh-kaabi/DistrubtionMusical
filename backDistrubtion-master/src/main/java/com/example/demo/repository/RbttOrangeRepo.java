package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.RbtOrange;

public interface RbttOrangeRepo extends JpaRepository<RbtOrange, Integer>{

	
	@Modifying 
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE rbt_orange u SET u.name = :name  , u.pic_byte = :picByte ,u.type = :type "
			+ "where u.id = (select * from (select max(id) from rbttt)xx)")
	public void savenewIn(@Param("name") String name, @Param("picByte") byte[] picByte, @Param("type") String type);




	@Query(nativeQuery = true, value = "select  language,album_nam_arb,album_nam_engl,cp_categ,cp_subcat,explicit_lycris,isprimary,label,name_artist,org_date,rating,role_1,version \n"
			+ "from rbt_orange  where   name_artist  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   album_nam_arb")
	List<Object[]> rbtOrangeByArtiste(@Param("id") Integer id);

}
