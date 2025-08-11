package com.example.demo.Interface;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Deezert {
	public Date d1;
	
	public Deezert(Object[] e) {
		
		System.out.println(ToStringBuilder.reflectionToString(e));
		//this.d1 = (Date) e[0];
		
	}


	

}
