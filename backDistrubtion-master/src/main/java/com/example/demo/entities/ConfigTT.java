package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="configTT")

public class ConfigTT {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String htva ;
	
	private String tax_Telcom ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public ConfigTT() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ConfigTT(Integer id, String htva, String tax_Telcom) {
		super();
		this.id = id;
		this.htva = htva;
		this.tax_Telcom = tax_Telcom;
	}

	public String getHtva() {
		return htva;
	}

	public void setHtva(String htva) {
		this.htva = htva;
	}

	public String getTax_Telcom() {
		return tax_Telcom;
	}

	public void setTax_Telcom(String tax_Telcom) {
		this.tax_Telcom = tax_Telcom;
	}

	@Override
	public String toString() {
		return "ConfigTT [id=" + id + ", htva=" + htva + ", tax_Telcom=" + tax_Telcom + "]";
	}

	

	
	
	
	
}

