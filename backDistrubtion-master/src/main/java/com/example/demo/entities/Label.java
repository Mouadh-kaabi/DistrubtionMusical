package com.example.demo.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="label")
public class Label  extends Users{

	
	
	
	
	/*@OneToMany(mappedBy = "label", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("label")
    private List<Artiste> artistes = new ArrayList<Artiste>();*/
	
	
	/* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	 @JoinColumn(name = "label_id")
	 private Set<Artiste> artistes = new HashSet<>();*/
	
	
	 /* @OneToMany(mappedBy = "label", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<Artiste> artistes;*/
	 
	/*@ManyToOne
	@JsonIgnoreProperties
	private Artiste user;*/
	
	
	


	public Label(String email,	String password) {
			super();
			this.email = email;
			
			this.password = password;
			
		}


	





















	public Label() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	


	public Label(Integer id, java.sql.Date cdate, java.sql.Date udate, String nom, String prenom, byte[] image,
			String picture, String name, String type, byte[] picByte, File[] files, Boolean pro, String nArtistique,
			String phone, String email, String password, java.sql.Date date, String nationnalite, String cin,
			java.sql.Date datecin, byte[] contrat, String pictureContrat, double part, double retenu,
			String proposition, String con, String url, String nameContrat, String typeContrat, String contratpdf,
			Set<Role> roles, List<Communication> communications) {
		super(id, cdate, udate, nom, prenom, image, picture, name, type, picByte, files, pro, nArtistique, phone, email,
				password, date, nationnalite, cin, datecin, contrat, pictureContrat, part, retenu, proposition, con, url,
				nameContrat, typeContrat, contratpdf, roles, communications);
		// TODO Auto-generated constructor stub
	}








	public Label(Integer id, String nom) {
		super(id, nom);
		// TODO Auto-generated constructor stub
	}


	public Label(String nom, String prenom, String phone, String email, String password) {
		super(nom, prenom, phone, email, password);
		// TODO Auto-generated constructor stub
	}


	public Label(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}


	


	

	
	 
	

	 
	 
}
