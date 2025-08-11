package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="deezer")
@DiscriminatorValue("deezer")
public class Deezer extends Details {

	
	
	
	
	
	
	public Deezer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
