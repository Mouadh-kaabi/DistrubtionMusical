package com.example.demo.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Platforme {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Integer id  ;	
		private  Date cdate  ;
		private  String nom  ;
		private  Date datep  ;
		private  float part  ;
		private  Date udate ;
		
		
		@ManyToMany(cascade = CascadeType.ALL)
		private Set<Chanson> chonsons; 

		
		@ManyToMany(cascade = CascadeType.ALL)
		private Set<Distributeur> distributeurs; 
	
		
		
		@ManyToOne
		private Details details;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getCdate() {
			return cdate;
		}

		public void setCdate(Date cdate) {
			this.cdate = cdate;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public Date getDatep() {
			return datep;
		}

		public void setDatep(Date datep) {
			this.datep = datep;
		}

		public float getPart() {
			return part;
		}

		public void setPart(float part) {
			this.part = part;
		}

		public Date getUdate() {
			return udate;
		}

		public void setUdate(Date udate) {
			this.udate = udate;
		}

		public Set<Chanson> getChonsons() {
			return chonsons;
		}

		public void setChonsons(Set<Chanson> chonsons) {
			this.chonsons = chonsons;
		}

		public Set<Distributeur> getDistributeurs() {
			return distributeurs;
		}

		public void setDistributeurs(Set<Distributeur> distributeurs) {
			this.distributeurs = distributeurs;
		} 
		
		
		
}
