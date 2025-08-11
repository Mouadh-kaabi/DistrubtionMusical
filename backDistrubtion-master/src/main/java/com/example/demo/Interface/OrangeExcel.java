package com.example.demo.Interface;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public interface OrangeExcel {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public java.sql.Date getDate1();
	
	

	public Date getDate2();
	
	public Double getTtc();
	
	public Double getQuantite();
	
	
	public Double getPart_smart();
	
	public Double getTax_telecom();
	
	public Double getPart_TTC();
	public Double getPart_artiste();
	
	public Double getHTVA();
	
	public String getNamea();
	
	public String getContent();
	
	public String getPlateforme();
	
	public String getCategory();
	
	
	
}
