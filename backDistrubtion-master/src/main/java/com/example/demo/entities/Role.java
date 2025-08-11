package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Role {

	
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    private ERole name;
    private String roleName;

    private String description;
    
    
    

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Role(Integer id, ERole name, String roleName, String description) {
		super();
		this.id = id;
		this.name = name;
		this.roleName = roleName;
		this.description = description;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
