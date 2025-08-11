package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.RbtOrange;
import com.example.demo.repository.RbttOrangeRepo;

@Service
public class RbtOrangeDao {

	
@Autowired
	
	RbttOrangeRepo rbttOrangeRepo ;
	
	
	public com.example.demo.entities.RbtOrange addRbt(com.example.demo.entities.RbtOrange rbtOrange)
	{
		return rbttOrangeRepo.save(rbtOrange);
	}
	
	
	public List<RbtOrange> getAllRbtTTOrange() {
		return rbttOrangeRepo.findAll();
	}
}
