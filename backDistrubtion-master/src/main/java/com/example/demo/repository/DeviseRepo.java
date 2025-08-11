package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Devise;

public interface DeviseRepo extends JpaRepository<Devise, Integer> {

	
	@Query(nativeQuery = true, value ="select cours  from devise where code='EUR' and datecours between :date1 and :date2"  )//
	Double getCourParDate(Date date1, Date date2);
	
	
}
