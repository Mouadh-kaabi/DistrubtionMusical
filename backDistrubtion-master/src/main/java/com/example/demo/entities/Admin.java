package com.example.demo.entities;

import javax.persistence.Entity;

@Entity(name="admin")
public class Admin extends Users {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Admin(String email,String password) {
		super();
		this.email = email;
		
		
		this.password = password;
	}
	
	
	public Admin(String name, String type, byte[] picByte) {

		this.name = name;
		this.type = type;
		this.picByte = picByte;

	}
	/*public Admin(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}*/
	
}