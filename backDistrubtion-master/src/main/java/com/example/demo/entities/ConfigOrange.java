package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="configOrange")

public class ConfigOrange {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private float htva ;
	
	private float tax_Telcom ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getHtva() {
		return htva;
	}

	public void setHtva(float htva) {
		this.htva = htva;
	}

	public float getTax_Telcom() {
		return tax_Telcom;
	}

	public void setTax_Telcom(float tax_Telcom) {
		this.tax_Telcom = tax_Telcom;
	}

	public ConfigOrange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfigOrange(Integer id, float htva, float tax_Telcom) {
		super();
		this.id = id;
		this.htva = htva;
		this.tax_Telcom = tax_Telcom;
	}
	
	
}
