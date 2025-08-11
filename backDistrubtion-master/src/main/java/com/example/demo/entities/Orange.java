package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="orange")
@DiscriminatorValue("orange")
public class Orange extends Details {

	
	
}
